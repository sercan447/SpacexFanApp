package com.example.spacexfanapp.di


import com.example.spacexfanapp.api.RocketService
import com.example.spacexfanapp.api.LaunchService
import com.example.spacexfanapp.helpers.Constants
import com.example.spacexfanapp.helpers.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providerBaseUrl() = Constants.BASE_URL

    /*
    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    */

    @Singleton
    @Provides
    fun provideRetrofitInstance(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideLaunchesService(retrofit: Retrofit): LaunchService = retrofit.create(LaunchService::class.java)

    @Provides
    fun provideRocketService(retrofit: Retrofit): RocketService = retrofit.create(RocketService::class.java)


}