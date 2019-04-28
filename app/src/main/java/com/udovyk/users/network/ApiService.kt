package com.udovyk.users.network

import com.udovyk.users.network.model.UserResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("./")
    fun getUsers(@Query("page") page: Int, @Query("results") results: Int): Observable<Response<UserResponse>>
}