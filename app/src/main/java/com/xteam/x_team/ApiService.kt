package com.xteam.x_team

import retrofit2.http.GET

interface ApiService {
    @GET("blogs")
    suspend fun getBlog(): List<Response>
}