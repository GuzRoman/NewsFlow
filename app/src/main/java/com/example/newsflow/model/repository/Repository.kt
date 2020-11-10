package com.example.newsflow.model.repository

import androidx.lifecycle.LiveData
import com.example.newsflow.model.models.NewsModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {

    suspend fun getWSJNews(): Flow<Result<NewsModel>>
}