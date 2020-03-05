package com.odhiambopaul.movies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.odhiambopaul.movies.data.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface Moviedao {
    //function to insert movies to the local database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie:MovieEntity):Completable

    //function to retrieve all the movies from the local database
    @Query("SELECT * FROM Movie")
    fun getAllMovies():Flowable<List<MovieEntity>>

}