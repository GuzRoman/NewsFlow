package com.example.newsflow.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsflow.model.models.NewsModel
import com.example.newsflow.model.network.api.NetService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(private val netService: NetService): Repository {

    override suspend fun getWSJNews(): LiveData<NewsModel> {
        val responce = withContext(Dispatchers.IO) {
            netService.
            getAllStreetJornal()
        }
        val liveData = MutableLiveData<NewsModel>()
        liveData.value = responce.body()
        return liveData
    }
}