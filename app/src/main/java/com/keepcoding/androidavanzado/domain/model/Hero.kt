package com.keepcoding.androidavanzado.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


// OLD SCHOOL -> MAL

@Entity(tableName = "heros")
data class Hero(
    @PrimaryKey @ColumnInfo(name = "id") @Json(name = "id") val id: String,
    @ColumnInfo(name = "name") @Json(name = "name") val name: String,
    @ColumnInfo(name = "photo") @Json(name = "photo") val photo: String
)





