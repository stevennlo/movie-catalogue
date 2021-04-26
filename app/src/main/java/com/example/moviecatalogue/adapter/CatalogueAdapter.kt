package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ComponentCatalogueBinding
import com.example.moviecatalogue.graphql.MediasQuery
import com.example.moviecatalogue.util.ImageUtil.loadImage

class CatalogueAdapter(private val clickListener: (Int) -> Unit) :
    ListAdapter<MediasQuery.Medium, CatalogueAdapter.ViewHolder>(ShowDiffCallback()) {
    class ViewHolder(
        private val binding: ComponentCatalogueBinding,
        private val clickListener: (Int) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MediasQuery.Medium) {
            binding.apply {
                catalogueRootCv.setOnClickListener {
                    clickListener.invoke(item.id)
                }
                loadImage(item.coverImage?.large, R.drawable.ic_default_movie, catalogueImageIv)
                catalogueTitleTv.text = item.title?.romaji
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ComponentCatalogueBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }
}

class ShowDiffCallback : DiffUtil.ItemCallback<MediasQuery.Medium>() {
    override fun areItemsTheSame(
        oldItem: MediasQuery.Medium,
        newItem: MediasQuery.Medium,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MediasQuery.Medium,
        newItem: MediasQuery.Medium,
    ): Boolean {
        return oldItem.id == newItem.id
    }

}
