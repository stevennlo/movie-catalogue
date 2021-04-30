package com.example.moviecatalogue.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CalendarModule {
    @Provides
    @Singleton
    fun provideCalendarInstance(): Calendar = Calendar.getInstance()
}