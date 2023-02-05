package com.example.newslogin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsClient private constructor() {
    val newsApi: NewsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsApi = retrofit.create(NewsApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"

        @get:Synchronized
        var instance: NewsClient? = null
            get() {
                if (field == null) {
                    field = NewsClient()
                }
                return field
            }
            private set
    }
}
