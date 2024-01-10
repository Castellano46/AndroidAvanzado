package com.keepcoding.androidavanzado.data.mappers

import com.keepcoding.androidavanzado.domain.model.HeroLocal
import com.keepcoding.androidavanzado.domain.model.HeroUI
import javax.inject.Inject

class LocalToUIMapper @Inject constructor() {

    fun map(localHeros: List<HeroLocal>): List<HeroUI>{
        return localHeros.map { HeroUI(it.name, it.photo) }
    }
}
