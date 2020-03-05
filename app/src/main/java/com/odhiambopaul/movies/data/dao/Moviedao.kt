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
    fun saveMovie(movie: MovieEntity): Completable

    /**
     * Completable concerned with execution completion whether the task has reach to completion or some error has occurred.*/

    //function to retrieve all the movies from the local database
    @Query("SELECT * FROM Movie")
    fun getAllMovies(): Flowable<List<MovieEntity>>
    /**
    If there is not any data in your database and the query return no row the Flowable will not emit, neither onNext,
    nor onError.
    “When there is already an entry of ModelClass object in your database, Flowable will trigger onNext.
    “Now if again you add new data or update your previous inserted object then the Flowable object will emit automatically,
    allowing you to update the UI based on the latest data.
     */

}