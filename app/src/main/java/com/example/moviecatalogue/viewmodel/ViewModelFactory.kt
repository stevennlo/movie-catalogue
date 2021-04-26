package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory<VM : ViewModel>(val provider: () -> VM) : ViewModelProvider.Factory {
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        return provider() as VM
    }
}