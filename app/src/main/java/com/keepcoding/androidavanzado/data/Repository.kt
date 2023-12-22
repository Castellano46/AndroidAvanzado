package com.keepcoding.androidavanzado.data

import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.keepcoding.androidavanzado.domain.model.Hero

class Repository {
    private val remoteDataSource = RemoteDataSource()


    suspend fun getHeroList(): List<Hero> {
        return remoteDataSource.getHeroList()
    }
}
