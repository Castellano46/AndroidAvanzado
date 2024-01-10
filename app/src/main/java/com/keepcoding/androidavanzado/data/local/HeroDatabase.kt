package com.keepcoding.androidavanzado.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keepcoding.androidavanzado.domain.model.HeroLocal

@Database(entities = [HeroLocal::class], version = 1)
abstract class HeroDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDAO
}
