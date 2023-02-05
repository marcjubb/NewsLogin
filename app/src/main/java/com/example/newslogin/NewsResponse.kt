package com.example.newslogin

import com.google.gson.annotations.SerializedName


class NewsResponse {
    @SerializedName("articles")
    var articles: List<Article>? = null
}
