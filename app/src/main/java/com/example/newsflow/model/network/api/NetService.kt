package com.example.newsflow.model.network.api

import com.example.newsflow.model.models.NewsModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=392b1990c7794786a8fb9e13cfbe09ed

const val KEY = "392b1990c7794786a8fb9e13cfbe09ed"
const val BASE_URL = "http://newsapi.org/v2/"

interface NetService {

    @GET("top-headlines")
    suspend fun getAllStreetJornal(
        @Query("country") country: String = "ru",
        @Query("category") category: String = "business"
    ): Response<NewsModel>


    companion object {
        operator fun invoke(): NetService {
            //Создаём интерсептор, куда встраиваем KEY для запросов
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
                .build()
                .create(NetService::class.java)
        }
    }
}