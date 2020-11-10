package com.example.newsflow.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsflow.model.models.NewsModel
import com.example.newsflow.model.network.api.NetService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

class RepositoryImpl(private val netService: NetService): Repository {

    override suspend fun getWSJNews(): Flow<Result<NewsModel>> =
        flow {
            withContext(Dispatchers.IO){}
            val data = try {
                netService.getAllStreetJornal()
            } catch (e: IOException){
                emit(Result.failure(e))
                return@flow
            }
            emit(Result.success(data))
        }.flowOn(Dispatchers.IO)


}