package com.keepcoding.androidavanzado.data.local

import com.keepcoding.androidavanzado.domain.model.HeroLocal
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: HeroDAO): LocalDataSourceInterface {

    override fun getHeros(): List<HeroLocal> {
        return dao.getAll()
    }

    override fun insertHeros(heros: List<HeroLocal>) {
        dao.insertAll(heros)
    }
}
