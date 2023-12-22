package com.keepcoding.androidavanzado.data.remote

import com.keepcoding.androidavanzado.data.remote.requests.HerosRequest
import com.keepcoding.androidavanzado.domain.model.Hero
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSource {

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder().addHeader("Authorization", "Bearer $TOKEN").build()
        chain.proceed(newRequest)
    }.build()

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl("https://dragonball.keepcoding.education/")
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    private var api = retrofit.create(DragonBallApi::class.java)

    suspend fun getHeroList(): List<Hero> {
        return api.getHeros(HerosRequest())
    }

    companion object {
        private const val TOKEN =
            "eyJhbGciOiJIUzI1NiIsImtpZCI6InByaXZhdGUiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjdDNzQ1NjRCLTQ5NUEtNDhCRC04QzIyLTM5OEUwOUREODY0MyIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6Imp1YW5qZS5jaWxsYTFAZ21haWwuY29tIn0.epMHxtAkVu_fT5FvQwKrm_fRqzT9UOG2gpiTTipQajw"
    }
}
