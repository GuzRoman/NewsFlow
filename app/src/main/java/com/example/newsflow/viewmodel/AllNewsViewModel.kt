package com.example.newsflow.viewmodel

import androidx.lifecycle.*
import com.example.newsflow.model.models.NewsModel
import com.example.newsflow.model.network.api.NetService
import com.example.newsflow.model.repository.RepositoryImpl
import kotlinx.coroutines.launch


class AllNewsViewModel : ViewModel() {

    private val repository = RepositoryImpl(NetService())
    val newsData: MutableLiveData<NewsModel> = MutableLiveData()

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch{
        fetchData()
    }

    private suspend fun fetchData(){
        val response = repository.getWSJNews()
        newsData.value = response.value
    }




}