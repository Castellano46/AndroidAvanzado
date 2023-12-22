package com.keepcoding.androidavanzado.data.remote

import com.keepcoding.androidavanzado.data.remote.requests.HerosRequest
import com.keepcoding.androidavanzado.domain.model.Hero
import retrofit2.http.Body
import retrofit2.http.POST

interface DragonBallApi {

    @POST("api/heros/all")
    suspend fun getHeros(@Body request: HerosRequest): List<Hero>
}
