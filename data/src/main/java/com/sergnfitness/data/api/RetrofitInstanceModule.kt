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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstanceModule {

//    var BASE_URL = // "https://gorest.co.in/public-api/"
//        "http://195.234.208.168:8085/"

    //"https://gorest.co.in/public-api/" //"https://velmm.com/apis/" //volley_array.json
    @Provides
    @Singleton
    fun getRetroInstance(): ApiServer {

        val loggin = HttpLoggingInterceptor()
        loggin.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggin)


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServer::class.java)
    }
///****************************************
//    @Provides
//    @Singleton
//    fun provideApiRepository(
//        api: ApiServer,
//        app: Application,
//        @Named("hello1") hello: String,
//    ): ApiRepository {
//        return ApiRepositoryImpl(api, app)
//    }
//
//    @Provides
//    @Singleton
//    @Named("hello1")
//    fun provideString1() = "Hello 1"
//
//    @Provides
//    @Singleton
//    @Named("hello2")
//    fun provideString2() = "Hello 2"
//
///**********************************************

    //    }
//    private val retrofit by lazy {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//    }
//
//    val api: ApiServer by lazy {
//        retrofit.create(ApiServer::class.java)
//    }

}
