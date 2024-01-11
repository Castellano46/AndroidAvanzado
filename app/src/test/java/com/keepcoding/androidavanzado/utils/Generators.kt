package com.keepcoding.androidavanzado.utils

import com.keepcoding.androidavanzado.domain.model.Hero
import com.keepcoding.androidavanzado.domain.model.HeroLocal
import com.keepcoding.androidavanzado.domain.model.HeroRemote

fun generateLocalHeros(size: Int) = (0 until size).map { HeroLocal("id$it", "name$it", "photo$it") }

fun generateRemoteHeros(size: Int) = (0 until size).map { HeroRemote("id$it", "name$it", "photo$it") }
