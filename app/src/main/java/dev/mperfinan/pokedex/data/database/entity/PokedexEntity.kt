package dev.mperfinan.pokedex.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokedex")
data class PokedexEntity(
    @PrimaryKey
    val id: Int,
)
