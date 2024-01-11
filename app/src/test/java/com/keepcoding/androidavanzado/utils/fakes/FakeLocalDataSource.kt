package com.keepcoding.androidavanzado.utils.fakes

import com.keepcoding.androidavanzado.data.local.LocalDataSourceInterface
import com.keepcoding.androidavanzado.domain.model.HeroLocal

class FakeLocalDataSource: LocalDataSourceInterface {

    private val database = mutableListOf<HeroLocal>()

    override fun getHeros(): List<HeroLocal>{
        return database
    }

    override fun insertHeros(heros: List<HeroLocal>){
        database.addAll(heros)
    }
}
