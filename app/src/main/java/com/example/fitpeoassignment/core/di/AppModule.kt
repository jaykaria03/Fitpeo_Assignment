package com.example.fitpeoassignment.core.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.fitpeoassignment.feature.photolist.data.PhotoApiService
import com.example.fitpeoassignment.feature.photolist.data.PhotoRepository
import com.example.fitpeoassignment.feature.photolist.data.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = "https://jsonplaceholder.typicode.com"

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun providePhotoApiService(retrofit: Retrofit): PhotoApiService =
        retrofit.create(PhotoApiService::class.java)

    @Provides
    @Singleton
    fun providePhotoRepository(apiService: PhotoApiService): PhotoRepository =
        PhotoRepositoryImpl(apiService)
}