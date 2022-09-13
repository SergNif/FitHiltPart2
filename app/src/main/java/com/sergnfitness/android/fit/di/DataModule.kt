package com.sergnfitness.android.fit.di

import android.content.Context
import com.sergnfitness.data.repository.UserRepositoryImpl
import com.sergnfitness.cleanarchitect.data.storage.SharedPrefsImplStorage
import com.sergnfitness.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPresImplStorage(@ApplicationContext context: Context): SharedPrefsImplStorage {
        return SharedPrefsImplStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(sharedPresInterfaceStorage: SharedPrefsImplStorage): UserRepository {
        return UserRepositoryImpl(sharedPrefsInterfaceStorage = sharedPresInterfaceStorage)//, appContext= Application())
    }



//    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
//        UserRepositoryImpl(sharedPresInterfaceStorage = SharedPresImplStorage(context = context))
//    }

}