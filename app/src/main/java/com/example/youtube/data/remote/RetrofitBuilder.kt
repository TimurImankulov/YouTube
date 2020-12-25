package com.example.youtube.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private var service: YoutubeService? = null

    fun getService(): YoutubeService? {
        if (service == null) service = buildRetrofit()

        return service
    }

    private fun buildRetrofit(): YoutubeService {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
            .create(YoutubeService::class.java)
    }

    private fun getClient(): OkHttpClient {              // обрабатываем TimeOutExecption

        val loggingInterceptor = HttpLoggingInterceptor().apply {// логируем запросы и ответы с сервера
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)                                      // добавляем Interceptor для логирования
            .addInterceptor(ApiKeyInterceptor())                                     // добаляем Interceptor для подстановки api key в запрос
            .build()
    }
}
