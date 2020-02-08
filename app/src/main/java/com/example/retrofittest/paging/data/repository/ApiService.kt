package com.example.retrofittest.paging.data.repository

import com.example.retrofittest.paging.data.remote.MovieData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val BASE_URL = "https://openapi.naver.com"
const val CLIENT_ID = "xxxxxxxxx"
const val CLIENT_KEY = "yyyyyyyy"

interface ApiService {

    @GET("/v1/search/movie")
    suspend fun fetchPosts(
        @Header("X-Naver-Client-Id") clientId: String = CLIENT_ID,
        @Header("X-Naver-Client-Secret") clientPw: String = CLIENT_KEY,
        @Query("query") query: String,
        @Query("start") pageCount: String
    ): Response<MovieData>
}