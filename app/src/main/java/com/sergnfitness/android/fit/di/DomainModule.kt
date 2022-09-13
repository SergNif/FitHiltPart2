package com.sergnfitness.android.fit.di

import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.repository.UserRepository
import com.sergnfitness.domain.usecase.GetUserOfEmailPasswordApiUseCase
import com.sergnfitness.domain.usecase.GetUserOfIdApiUseCase
import com.sergnfitness.domain.usecase.GetUserSharedPreferenceUseCase
import com.sergnfitness.domain.usecase.SaveUserSharedPreferenceUseCase
import com.sergnfitness.domain.usecase.putGetUserClass.GetUserClassFromSharedPreferenceUseCase
import com.sergnfitness.domain.usecase.putGetUserClass.SaveAllSharedPreferenceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetUserSharedPreferenceUseCase(userRepository: UserRepository): GetUserSharedPreferenceUseCase{
        return GetUserSharedPreferenceUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideSaveUserSharedPreferenceUseCase(userRepository: UserRepository): SaveUserSharedPreferenceUseCase{
        return SaveUserSharedPreferenceUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideGetUserClassFromSharedPreferenceUseCase(userRepository: UserRepository): GetUserClassFromSharedPreferenceUseCase {
        return GetUserClassFromSharedPreferenceUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideSaveAllSharedPreferenceUseCase(userRepository: UserRepository): SaveAllSharedPreferenceUseCase {
        return SaveAllSharedPreferenceUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideGetUserOfIdApiUseCase(apiRepository: ApiRepository): GetUserOfIdApiUseCase{
        return GetUserOfIdApiUseCase(repository = apiRepository)
    }

    @Provides
    fun provideGetUserOfEmailPasswordApiUseCase(apiRepository: ApiRepository): GetUserOfEmailPasswordApiUseCase{
        return GetUserOfEmailPasswordApiUseCase(repository = apiRepository)
    }


//    private val saveUserSharedPreferenceUseCase by lazy(LazyThreadSafetyMode.NONE) {
//        SaveUserSharedPreferenceUseCase(userRepository = userRepository)
//    }
//    private val getUserSharedPreferenceUseCase by lazy(LazyThreadSafetyMode.NONE) {
//        GetUserSharedPreferenceUseCase(userRepository = userRepository)
//    }
}