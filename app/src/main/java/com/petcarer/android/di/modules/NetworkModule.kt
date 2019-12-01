package com.petcarer.android.di.modules

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.petcarer.android.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)


    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache =
        Cache(application.cacheDir, 10L * 1024L * 1024L)

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .cache(cache)
        return builder.build()
    }

    //TODO replace empty string with BASE URL
    @Provides
    @Singleton
    fun provideRetrofit(gson: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(gson)
            .baseUrl("https://openapis.org")
            .client(okHttpClient)
            .build()
}