package com.cvs.code.challenge.di

import android.content.Context
import com.android.code.challenge.justo.data.retrofit.ImagesService
import com.cvs.code.challenge.data.api.ImagesServiceHelper
import com.cvs.code.challenge.data.api.ImagesServiceImpl
import com.cvs.code.challenge.data.helpers.UtilStrings.BASE_URL
import com.cvs.code.challenge.data.repository.ImagesRepositoryImpl
import com.cvs.code.challenge.domain.usecase.GetImageSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImagesModule {

    @Singleton
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL : String) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideImagesSearchService(retrofit: Retrofit) = retrofit.create(ImagesService::class.java)

    @Singleton
    @Provides
    fun provideImagesSearchServiceHelper(imagesService: ImagesServiceImpl): ImagesServiceHelper = imagesService

    @Singleton
    @Provides
    fun provideRepository(
        imagesServiceHelper: ImagesServiceHelper,
    ) = ImagesRepositoryImpl(imagesServiceHelper, Dispatchers.IO)

    @Singleton
    @Provides
    fun provideGetImageSearchUseCase(imageRepository:ImagesRepositoryImpl) = GetImageSearchUseCase(imageRepository)
}