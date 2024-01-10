package com.keepcoding.androidavanzado.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

// NEW SCHOOL -> BIEN
@Entity(tableName = "heros")
data class HeroLocal(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: String,

)

data class HeroRemote(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "photo") val photo: String
)

data class HeroUI(
    val name:  String,
    val photo: String
)



fun HeroRemote.mapToLocal(): HeroLocal {
    return HeroLocal(this.id, this.name, this.photo)
}
