package com.example.reahometime.di

import com.example.reahometime.api.HometimeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideHometimeApi(): HometimeApi {
        return HometimeApi.create()
    }
}