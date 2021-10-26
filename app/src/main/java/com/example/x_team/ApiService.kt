package com.example.x_team

import retrofit2.http.GET

interface ApiService {
    @GET("user")
    suspend fun getBlog(): List<Response>
}