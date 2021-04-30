package com.example.moviecatalogue.util

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoUtil {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}