package com.example.youtube.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {   // класс который подставляет Query в запросы, модифицирует запросы

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()           // оригинальный запрос без api key
        val originalHttpUrl = original.url        // записывеам в переменную оригинальную ссылку

        val url = originalHttpUrl.newBuilder()    // создаем новую ссылку, к оригинальной ссылке добавляем параметр
            .addQueryParameter("key", "AIzaSyC5xOKtR05SHjyWVvBXC6850AMbO4_JkLo")
            .build()

        val requestBuilder = original.newBuilder() // создали новый запрос с новой ссылкой
            .url(url)
            .build()

        return chain.proceed(requestBuilder)
    }
}