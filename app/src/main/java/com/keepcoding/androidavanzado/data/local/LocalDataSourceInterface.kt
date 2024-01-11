package com.keepcoding.androidavanzado.data.local

import com.keepcoding.androidavanzado.domain.model.HeroLocal

interface LocalDataSourceInterface {
    fun getHeros(): List<HeroLocal>
    fun insertHeros(heros: List<HeroLocal>)
}
