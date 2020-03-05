package com.odhiambopaul.movies.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val poster_path: String,
    val vote_average: Float,
    val title: String,
    val overview: String,
    val release_date: String,
    val id: Int
)