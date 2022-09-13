package com.sergnfitness.data.api


import android.app.Application
import com.sergnfitness.data.repository.ApiRepositoryImpl
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModuleData {

//    @Provides
//    fun logging() = HttpLoggingInterceptor()
//        .setLevel(HttpLoggingInterceptor.Level.BODY)
//
//    @Provides
//    fun okHttpClient() = OkHttpClient.Builder()
//        .addInterceptor(logging())
//        .build()
//
//    @Provides
//    @Singleton
//    fun providePaprikaApi(): ApiServer {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient())
//            .build()
//            .create(ApiServer::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiRepository(api: ApiServer, app: Application): ApiRepository{
//        return ApiRepositoryImpl(api, app)
//    }

}