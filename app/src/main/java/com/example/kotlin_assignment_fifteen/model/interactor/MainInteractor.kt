package com.example.kotlin_assignment_fifteen.model.interactor

import com.example.kotlin_assignment_fifteen.model.response.MovieResponse

class MainInteractor {

    interface OnFinishedListener {
        fun onResultSuccess(dataMovie: List<MovieResponse>)
        fun onResultFail(strError: String)
    }

    fun requestGetDataAPI(onFinishedListener: OnFinishedListener) {
        // Get data from server
    }
}