package com.example.petsapp.di

import android.content.Context
import com.example.petsapp.data.repo.IPreferences
import com.example.petsapp.data.repo.MyRepository
import com.example.petsapp.data.repo.MyRepositoryImpl
import com.example.petsapp.data.repo.PreferencesImpl
import com.example.petsapp.data.service.MyService
import com.example.petsapp.utils.backendUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideRetrofit(
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(backendUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideMyService(
        retrofit: Retrofit
    ): MyService = retrofit.create(MyService::class.java)

    @Provides
    fun provideMyRepository(
        service: MyService
    ): MyRepository = MyRepositoryImpl(service)

    @Provides
    fun providePrefencesRepository(
        @ApplicationContext context: Context
    ): IPreferences = PreferencesImpl(context)
}