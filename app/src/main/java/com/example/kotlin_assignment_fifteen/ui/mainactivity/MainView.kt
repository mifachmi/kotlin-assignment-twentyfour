package com.example.kotlin_assignment_fifteen.ui.mainactivity

import com.example.kotlin_assignment_fifteen.model.response.MovieResponse

interface MainView {
    fun setDataToRecycleView(dataMovie: List<MovieResponse>)
    fun hideProgressBar()
    fun showProgressBar()
    fun getDataFailed(strError: String)
}