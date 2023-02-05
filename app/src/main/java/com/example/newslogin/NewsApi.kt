package com.example.newslogin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("apiKey") apiKey: String?,
        @Query("sources") sources: String?
    ): Call<NewsResponse?>?
}
