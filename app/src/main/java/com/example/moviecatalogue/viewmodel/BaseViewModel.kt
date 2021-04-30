package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalogue.util.EspressoUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected fun launchViewModelScope(block: suspend () -> Unit) {
        EspressoUtil.increment()
        viewModelScope.launch(Dispatchers.IO) {
            block.invoke()
            EspressoUtil.decrement()
        }
    }
}