package com.example.newsflow.model.repository

import androidx.lifecycle.LiveData
import com.example.newsflow.model.models.NewsModel

interface Repository {

    suspend fun getWSJNews(): LiveData<NewsModel>
}