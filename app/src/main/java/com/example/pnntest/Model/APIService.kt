package com.example.pnntest.Model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {
    @GET("dbs/movies.json?print=pretty")
    fun getFilm() : Deferred<Response<Array<Film>>>
}

object NetworkService {
    private val baseURL = "https://kaverin-ddb.firebaseio.com/"
    private val retrofit = Retrofit.Builder().baseUrl(baseURL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService() = retrofit.create(APIService::class.java)
}