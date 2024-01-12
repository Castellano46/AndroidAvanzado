package com.keepcoding.androidavanzado.utils

import com.keepcoding.androidavanzado.domain.model.Hero
import com.keepcoding.androidavanzado.domain.model.HeroLocal
import com.keepcoding.androidavanzado.domain.model.HeroRemote
import com.keepcoding.androidavanzado.domain.model.HeroUI

fun generateLocalHeros(size: Int) = (0 until size).map { HeroLocal("id$it", "name$it", "photo$it") }

fun generateRemoteHeros(size: Int) = (0 until size).map { HeroRemote("id$it", "name$it", "photo$it") }

fun generateUIHeros(size: Int) = (0 until size).map { HeroUI("id$it", "name$it") }
