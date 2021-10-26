package com.example.x_team

import retrofit2.http.GET

interface ApiService {
    @GET("user")
    suspend fun getUser(): List<Response>
}