package com.example.newsflow.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsflow.model.models.NewsModel
import com.example.newsflow.model.network.api.NetService

class RepositoryImpl(private val netService: NetService): Repository {

    override suspend fun getWSJNews(): LiveData<NewsModel> {
        val responce = netService.getAllStreetJornal()
        val liveData = MutableLiveData<NewsModel>()
        liveData.postValue(responce.execute().body())
        return liveData
    }
}