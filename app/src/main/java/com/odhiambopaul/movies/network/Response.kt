package com.odhiambopaul.movies.network

import com.odhiambopaul.movies.data.entity.MovieEntity

data class Response(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieEntity>
)

