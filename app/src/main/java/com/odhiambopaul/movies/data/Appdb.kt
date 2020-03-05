package com.odhiambopaul.movies.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.odhiambopaul.movies.data.dao.Moviedao
import com.odhiambopaul.movies.data.entity.MovieEntity

//provide the list of entity available in the app and the db version to the @Database annotation
@Database(entities = [MovieEntity::class], version = 1)
abstract class Appdb : RoomDatabase() {
    abstract fun moviedao(): Moviedao

    companion object {
        @Volatile
        private var INSTANCE: Appdb? = null

        fun getInstance(context: Context): Appdb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                Appdb::class.java, "Movie.db"
            )
                .build()
    }
}