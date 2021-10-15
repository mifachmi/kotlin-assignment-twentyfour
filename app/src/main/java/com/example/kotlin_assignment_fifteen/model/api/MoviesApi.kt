package com.example.kotlin_assignment_fifteen.model.api

import com.example.kotlin_assignment_fifteen.model.response.DetailMovieResponse
import com.example.kotlin_assignment_fifteen.model.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
    @GET("movie/popular?api_key=c67f6007c9a1b07837648440ce6b1cc5&language=en-US&page=1")
    fun getMovies(): Call<MovieResponse>

    @GET("movie/{movie_id}?api_key=c67f6007c9a1b07837648440ce6b1cc5&language=en-US&page=1")
    fun getDetailMovie(
        @Path("movie_id") movieId: String
    ): Call<DetailMovieResponse>
}