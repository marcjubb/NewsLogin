package com.example.newslogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsActivity : AppCompatActivity() {
    private lateinit var newsApi: NewsApi
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        recyclerView = findViewById(R.id.news_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsApi = retrofit.create(NewsApi::class.java)
        val call: Call<NewsResponse?>? = newsApi.getTopHeadlines("a1a0bc0715b64338839fc4422ca4ef8a", "bbc-news,cnn")
        call!!.enqueue(object : Callback<NewsResponse?> {
            override fun onResponse(call: Call<NewsResponse?>, response: Response<NewsResponse?>) {
                if (response.isSuccessful()) {
                    val newsResponse: NewsResponse? = response.body()
                    val articles: List<Article>? = newsResponse?.articles
                    val recyclerView = findViewById<RecyclerView>(R.id.news_recycler_view)

                    recyclerView.adapter = ArticleAdapter(articles!!)
                    System.out.println(articles[2].urlToImage)
                    System.out.println(articles[4].urlToImage)
                    System.out.println(articles[7].urlToImage)
                    System.out.println(articles[1].urlToImage)
                    System.out.println(articles[3].urlToImage)
                    System.out.println(articles[8].urlToImage)
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                // Handle the failure
            }
        })
    }
}

