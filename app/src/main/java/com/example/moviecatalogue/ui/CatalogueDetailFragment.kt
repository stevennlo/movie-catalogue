package com.example.moviecatalogue.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.CharacterAdapter
import com.example.moviecatalogue.databinding.FragmentCatalogueDetailBinding
import com.example.moviecatalogue.graphql.MediaQuery
import com.example.moviecatalogue.repository.MediaRepository
import com.example.moviecatalogue.service.Status
import com.example.moviecatalogue.util.HtmlUtil
import com.example.moviecatalogue.util.ImageUtil.getDefaultItemDecoration
import com.example.moviecatalogue.util.ImageUtil.getEmoResourceId
import com.example.moviecatalogue.util.ImageUtil.loadImage
import com.example.moviecatalogue.util.MessageType
import com.example.moviecatalogue.util.getColorFromAttr
import com.example.moviecatalogue.util.getOrDefault
import com.example.moviecatalogue.viewmodel.CatalogueDetailViewModel
import com.example.moviecatalogue.viewmodel.CatalogueViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.material.chip.Chip

class CatalogueDetailFragment(viewModel: CatalogueViewModel? = null) :
    BaseFragment<FragmentCatalogueDetailBinding>(FragmentCatalogueDetailBinding::inflate) {
    private val viewModel: CatalogueDetailViewModel by viewModels(factoryProducer = {
        ViewModelFactory {
            viewModel ?: CatalogueDetailViewModel(MediaRepository())
        }
    })
    private val args: CatalogueDetailFragmentArgs by this.navArgs()
    private var mediaId: Int = 0
    private val characterAdapter = CharacterAdapter()

    override fun runOnCreateView() {
        super.runOnCreateView()
        binding.apply {
            catalogueDetailRefreshSrl.setOnRefreshListener {
                loadData()
            }
            catalogueDetailDescriptionTv.apply {
                movementMethod = ScrollingMovementMethod()
                setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            v.parent.requestDisallowInterceptTouchEvent(true)
                            v.performClick()
                        }
                    }
                    false
                }
            }
            catalogueDetailCharacterListRv.apply {
                adapter = characterAdapter
                addItemDecoration(getDefaultItemDecoration(mContext))
            }
            viewModel.media.observe(viewLifecycleOwner, { result ->
                when (result.status) {
                    Status.StatusType.FAILED -> {
                        showResult(
                            MessageType.ERROR,
                            result.message
                                ?: getString(R.string.unknown_error_message)
                        )
                    }
                    Status.StatusType.SUCCESS -> {
                        result.data?.let {
                            updateLayout(it)
                        }
                        showResult(MessageType.EXISTS)
                    }
                }
                catalogueDetailRefreshSrl.isRefreshing = false
            })
        }
    }

    private fun updateLayout(item: MediaQuery.Media) {
        binding.apply {
            loadImage(item.coverImage?.large,
                R.drawable.ic_default_movie,
                catalogueDetailImageSiv)
            loadImage(item.bannerImage,
                R.drawable.ic_default_movie,
                catalogueDetailBannerSiv)
            catalogueDetailGenreCg.apply {
                removeAllViews()
                item.genres?.forEach { genre ->
                    val chip = Chip(mContext).apply {
                        chipBackgroundColor =
                            ColorStateList.valueOf(mContext.getColorFromAttr(R.attr.colorSurface))
                        setChipStrokeWidthResource(R.dimen.chip_stroke_width)
                        item.coverImage?.color?.let { color ->
                            val colorState =
                                ColorStateList.valueOf(Color.parseColor(color))
                            chipStrokeColor = colorState
                            setTextColor(colorState)
                        }
                        setEnsureMinTouchTargetSize(false)
                        text = genre
                    }
                    addView(chip)
                }
            }
            catalogueDetailTitleTv.text =
                item.title?.romaji.getOrDefault(mContext)
            catalogueDetailDescriptionTv.text =
                HtmlUtil.fromHtml(item.description.getOrDefault(mContext))
            catalogueDetailScoreTv.text = String.format(getString(R.string.score_value),
                item.averageScore.getOrDefault(mContext))
            catalogueDetailScoreTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0,
                0,
                getEmoResourceId(item.averageScore),
                0)
            catalogueDetailFavoriteTv.text = item.favourites.getOrDefault(mContext, true)
            catalogueDetailEpisodeTv.text = item.episodes.getOrDefault(mContext)
            catalogueDetailDurationTv.text = item.duration.getOrDefault(mContext)
            item.characters?.edges?.filterNotNull().let { characters ->
                updateRecyclerView(characters?.size ?: 0)
                characterAdapter.submitList(characters)
            }
        }
    }

    private fun updateRecyclerView(size: Int) {
        (binding.catalogueDetailCharacterListRv.layoutParams as ViewGroup.LayoutParams).let {
            if (size > 3) {
                it.height = resources.getDimension(R.dimen.character_list_height).toInt()
            } else {
                it.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaId = args.mediaId
        loadData()
    }

    private fun loadData() {
        viewModel.getCatalogueDetail(mediaId)
    }

    override fun getRootViewGroup(): ViewGroup = binding.catalogueDetailRootCl
}