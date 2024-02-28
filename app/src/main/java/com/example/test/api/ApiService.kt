package com.example.test.api

import retrofit.Call
import retrofit.http.GET

interface ApiService {

    @GET("v0/books")
    fun fetchAllBooks(): Call<List<ListBookEntity>>
}