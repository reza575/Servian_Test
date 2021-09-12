package com.moeiny.reza.servian_test.core.di

import android.app.Application
import android.content.Context
import com.moeiny.reza.servian_test.data.photorepository.PhotoRepository
import com.moeiny.reza.servian_test.data.photorepository.PhotoRepositoryDefault
import com.moeiny.reza.servian_test.data.retrofit.ApiService
import com.moeiny.reza.servian_test.data.userrepository.UserRepository
import com.moeiny.reza.servian_test.data.userrepository.UserRepositoryDefault
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {
    @Singleton
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideUserRepository(userRepository: UserRepositoryDefault): UserRepository = userRepository

    @Singleton
    @Provides
    fun providePhotoRepository(photoRepository: PhotoRepositoryDefault): PhotoRepository = photoRepository

    @Singleton
    @Provides
    fun provideApiService(retrofit:Retrofit):ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}