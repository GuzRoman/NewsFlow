package com.example.newsflow.model.models




data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)