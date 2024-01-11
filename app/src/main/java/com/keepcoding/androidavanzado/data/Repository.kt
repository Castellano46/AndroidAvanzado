package com.keepcoding.androidavanzado.data

import android.content.Context
import android.util.Log
import com.keepcoding.androidavanzado.data.local.LocalDataSource
import com.keepcoding.androidavanzado.data.local.LocalDataSourceInterface
import com.keepcoding.androidavanzado.data.mappers.LocalToUIMapper
import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.keepcoding.androidavanzado.domain.model.HeroLocal
import com.keepcoding.androidavanzado.domain.model.HeroRemote
import com.keepcoding.androidavanzado.domain.model.HeroUI
import com.keepcoding.androidavanzado.domain.model.mapToLocal
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSourceInterface,
    private val remoteDataSource: RemoteDataSource,
    private val localToUIMapper: LocalToUIMapper
) {

    suspend fun getHeroList(): List<HeroUI> {
        val localHeros: List<HeroLocal> = localDataSource.getHeros()

        return if (localHeros.isNotEmpty()) {
            localToUIMapper.map(localHeros)
        } else {
            val remoteHeros: List<HeroRemote> = remoteDataSource.getHeroList()
            localDataSource.insertHeros(remoteHeros.map { it.mapToLocal() })

            val updatedLocalHeros: List<HeroLocal> = localDataSource.getHeros()
            localToUIMapper.map(updatedLocalHeros)
        }
    }
}
