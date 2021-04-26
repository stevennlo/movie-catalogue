package com.example.moviecatalogue.ui

import android.view.ViewGroup
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.CataloguePagerAdapter
import com.example.moviecatalogue.databinding.FragmentCatalogueBinding
import com.example.moviecatalogue.util.MessageType
import com.google.android.material.tabs.TabLayoutMediator

class CatalogueFragment :
    BaseFragment<FragmentCatalogueBinding>(FragmentCatalogueBinding::inflate) {
    override fun runOnCreateView() {
        super.runOnCreateView()
        showResult(MessageType.EXISTS)
        binding.apply {
            val cataloguePagerAdapter =
                CataloguePagerAdapter(this@CatalogueFragment)
            catalogueViewVp2.isUserInputEnabled = false
            catalogueViewVp2.adapter = cataloguePagerAdapter
            (catalogueViewVp2.getChildAt(0) as ViewGroup).clipChildren = false
            TabLayoutMediator(catalogueTabsTl, catalogueViewVp2) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.tv_show_tab)
                    1 -> tab.text = getString(R.string.movie_tab)
                }
            }.attach()
        }
    }
}