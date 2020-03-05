package com.odhiambopaul.movies.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi{

    @GET("/3/movie/popular")
    fun getMovies(@Query("api_key") key:String):Observable<Response>

    @GET("/3/movie/top_rated")
    fun getTopMovies(@Query("api_key") key:String):Observable<Response>


}