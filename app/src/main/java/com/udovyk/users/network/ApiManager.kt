package com.udovyk.users.network

import com.udovyk.users.network.model.UserResponse
import io.reactivex.Observable
import retrofit2.Response

class ApiManager (val apiService: ApiService) {

    fun getUsers(page: Int, results: Int): Observable<Response<UserResponse>> {
        return apiService.getUsers(page, results)
    }
}