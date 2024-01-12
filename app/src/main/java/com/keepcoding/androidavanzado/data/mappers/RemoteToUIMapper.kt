package com.keepcoding.androidavanzado.data.mappers

import com.keepcoding.androidavanzado.data.remote.response.HeroDetailRemote
import com.keepcoding.androidavanzado.domain.model.HeroDetailUI
import javax.inject.Inject

class RemoteToUIMapper @Inject constructor() {

    fun map(heroDetailRemote: HeroDetailRemote): HeroDetailUI = with(heroDetailRemote) {
        HeroDetailUI(name, photo, description)
    }


}
