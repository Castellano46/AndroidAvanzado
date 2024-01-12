package com.keepcoding.androidavanzado.data.remote

import com.keepcoding.androidavanzado.data.remote.requests.HerosRequest
import com.keepcoding.androidavanzado.data.remote.response.HeroDetailRemote
import com.keepcoding.androidavanzado.domain.model.HeroRemote
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: DragonBallApi) {

    suspend fun getHeroList(): List<HeroRemote> {
        return api.getHeros(HerosRequest())
    }

    suspend fun getHeroDetail(name: String): HeroDetailRemote {
        return api.getHerosDetail(HerosRequest(name)).first()
    }


    companion object {
        const val TOKEN =
            "eyJhbGciOiJIUzI1NiIsImtpZCI6InByaXZhdGUiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjdDNzQ1NjRCLTQ5NUEtNDhCRC04QzIyLTM5OEUwOUREODY0MyIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6Imp1YW5qZS5jaWxsYTFAZ21haWwuY29tIn0.epMHxtAkVu_fT5FvQwKrm_fRqzT9UOG2gpiTTipQajw"
    }
}
