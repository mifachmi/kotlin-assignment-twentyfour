package com.example.kotlin_assignment_fifteen.ui.detailactivity

import com.example.kotlin_assignment_fifteen.model.response.DetailMovieResponse

interface DetailMovieView {
    fun setDetailMovie(dataMovie: DetailMovieResponse)
    fun getDataFailed(strError: String)
}