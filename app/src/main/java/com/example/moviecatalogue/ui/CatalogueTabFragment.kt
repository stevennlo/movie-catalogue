package com.example.moviecatalogue.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.CatalogueAdapter
import com.example.moviecatalogue.databinding.FragmentCatalogueTabBinding
import com.example.moviecatalogue.graphql.type.MediaFormat
import com.example.moviecatalogue.repository.MediaRepository
import com.example.moviecatalogue.service.Status
import com.example.moviecatalogue.util.MessageType
import com.example.moviecatalogue.viewmodel.CatalogueViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class CatalogueTabFragment(private val format: MediaFormat, viewModel: CatalogueViewModel? = null) :
    BaseFragment<FragmentCatalogueTabBinding>(FragmentCatalogueTabBinding::inflate) {
    private val viewModel: CatalogueViewModel by viewModels(factoryProducer = {
        ViewModelFactory {
            viewModel ?: CatalogueViewModel(MediaRepository())
        }
    })
    private val catalogueAdapter by lazy {
        CatalogueAdapter(this::navigateToDetail)
    }

    override fun runOnCreateView() {
        super.runOnCreateView()
        binding.apply {
            catalogueTabRefreshSrl.setOnRefreshListener {
                loadData()
            }
            catalogueTabListRv.apply {
                adapter = catalogueAdapter
                layoutManager = GridLayoutManager(mContext, 2)
                viewModel.medias.observe(viewLifecycleOwner, { result ->
                    when {
                        result.status == Status.StatusType.FAILED -> {
                            showResult(
                                MessageType.ERROR,
                                result.message
                                    ?: getString(R.string.unknown_error_message)
                            )
                        }
                        (result.data?.size ?: 0) == 0 -> {
                            showResult(
                                MessageType.NOT_FOUND,
                                getString(R.string.nothing_here)
                            )
                        }
                        else -> showResult(MessageType.EXISTS)
                    }
                    catalogueTabRefreshSrl.isRefreshing = false
                    catalogueAdapter.submitList(result.data)
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    private fun loadData() {
        viewModel.getCatalogue(format)
    }

    private fun navigateToDetail(mediaId: Int) {
        val destination =
            CatalogueFragmentDirections.actionCatalogueFragmentToCatalogueDetailFragment(mediaId)
        findNavController().navigate(destination)
    }

    override fun getRootViewGroup(): ViewGroup = binding.catalogueTabRootCl
}