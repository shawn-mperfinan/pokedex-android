package dev.mperfinan.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.mperfinan.pokedex.data.database.dao.NewsDao
import dev.mperfinan.pokedex.data.database.dao.PokedexDao
import dev.mperfinan.pokedex.data.database.entity.NewsEntity
import dev.mperfinan.pokedex.data.database.entity.PokedexEntity

@Database(
    entities = [NewsEntity::class, PokedexEntity::class],
    version = 1,
)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

    abstract fun pokedexDao(): PokedexDao
}
