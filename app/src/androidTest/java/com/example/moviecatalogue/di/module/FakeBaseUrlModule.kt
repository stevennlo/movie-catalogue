package com.example.moviecatalogue.di.module

import com.example.moviecatalogue.data.DummyData.BASE_TEST_PORT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FakeBaseUrlModule {
    @Provides
    fun provideBaseUrl() = "http://localhost:$BASE_TEST_PORT"
}