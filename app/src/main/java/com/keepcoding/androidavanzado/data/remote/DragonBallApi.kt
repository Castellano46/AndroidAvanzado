package com.keepcoding.androidavanzado.data.remote

import com.keepcoding.androidavanzado.data.remote.requests.HerosRequest
import com.keepcoding.androidavanzado.domain.model.HeroRemote
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

interface DragonBallApi {

    @POST("api/heros/all")
    suspend fun getHeros(@Body request: HerosRequest): List<HeroRemote>

    @POST("api/heros/all")
    suspend fun getHeros2(@Body request: HerosRequest): List<HeroRemote>
}
