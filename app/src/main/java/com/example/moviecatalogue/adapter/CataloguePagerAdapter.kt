package com.example.moviecatalogue.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviecatalogue.graphql.type.MediaFormat
import com.example.moviecatalogue.ui.CatalogueTabFragment

class CataloguePagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CatalogueTabFragment(MediaFormat.TV)
            else -> CatalogueTabFragment(MediaFormat.MOVIE)
        }
    }
}