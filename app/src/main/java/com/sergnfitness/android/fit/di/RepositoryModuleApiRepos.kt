package com.sergnfitness.android.fit.di

import com.sergnfitness.data.repository.ApiRepositoryImpl
import com.sergnfitness.domain.repository.ApiRepository

import dagger.Binds
import dagger.Module

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModuleApiRepos {

    @Binds
    @Singleton
    abstract fun bindApiRepository(
        apiRepositoryImpl: ApiRepositoryImpl
    ): ApiRepository


}