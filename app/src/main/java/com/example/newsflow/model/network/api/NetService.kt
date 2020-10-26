package com.example.newsflow.model.network.api

import androidx.lifecycle.LiveData
import com.example.newsflow.model.models.NewsModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val KEY = "392b1990c7794786a8fb9e13cfbe09ed"
const val BASE_URL = "http://newsapi.org/v2/"

interface NetService {

    @GET("everything?domains=wsj.com")
    suspend fun getAllStreetJornal(): Call<NewsModel>

    companion object {
        operator fun invoke(): NetService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("apiKey", KEY)
                    .build()

                val requirest = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(requirest)

            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(NetService::class.java)
        }
    }
}