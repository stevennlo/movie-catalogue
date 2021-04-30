package com.example.moviecatalogue.di.module

import com.example.moviecatalogue.util.BASE_ANILIST_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class BaseUrlModule {
    @Provides
    fun provideBaseUrl() = BASE_ANILIST_URL
}