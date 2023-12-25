package com.example.theguardian_news_app.models

data class NewsAPI_resp(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)