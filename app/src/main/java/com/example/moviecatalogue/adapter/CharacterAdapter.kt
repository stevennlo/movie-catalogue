package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ComponentCharacterBinding
import com.example.moviecatalogue.graphql.MediaQuery
import com.example.moviecatalogue.util.ImageUtil.loadImage
import com.example.moviecatalogue.util.getOrDefault
import java.util.*

class CharacterAdapter :
    ListAdapter<MediaQuery.Edge, CharacterAdapter.ViewHolder>(CharacterEdgeDiffUtil()) {
    class ViewHolder(private val binding: ComponentCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MediaQuery.Edge) {
            binding.apply {
                val context = root.context
                item.node?.let {
                    loadImage(it.image?.large, R.drawable.ic_default_person, characterImageIv)
                    characterNameTv.text =
                        it.name?.full.getOrDefault(context)
                }
                characterRoleTv.text =
                    item.role?.toString()?.getOrDefault(context)?.toLowerCase(Locale.ROOT)
                        ?.capitalize(Locale.ROOT)
                item.voiceActors?.firstOrNull()?.let {
                    loadImage(it.image?.large,
                        R.drawable.ic_default_person,
                        characterActorImageIv)
                    characterActorNameTv.text =
                        it.name?.full.getOrDefault(context)
                    characterActorLanguageTv.text =
                        it.languageV2.getOrDefault(context)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ComponentCharacterBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }
}

class CharacterEdgeDiffUtil : DiffUtil.ItemCallback<MediaQuery.Edge>() {
    override fun areItemsTheSame(oldItem: MediaQuery.Edge, newItem: MediaQuery.Edge): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MediaQuery.Edge, newItem: MediaQuery.Edge): Boolean {
        return oldItem.id == newItem.id
    }
}
