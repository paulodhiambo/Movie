package com.odhiambopaul.movies.network

import com.odhiambopaul.movies.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val okHttpClient=OkHttpClient
        .Builder()
        .build()

    private val retrofit=Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(MovieApi::class.java)

    fun buildservice():MovieApi{
        return retrofit
    }
}