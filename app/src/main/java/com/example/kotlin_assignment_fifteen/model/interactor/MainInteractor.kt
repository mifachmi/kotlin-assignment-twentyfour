package com.example.kotlin_assignment_fifteen.model.interactor

import com.example.kotlin_assignment_fifteen.model.network.NetworkConfig
import com.example.kotlin_assignment_fifteen.model.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInteractor {

    interface OnFinishedListener {
        fun onResultSuccess(dataMovie: List<MovieResponse>)
        fun onResultFail(strError: String)
    }

    fun requestGetDataAPI(onFinishedListener: OnFinishedListener) {
        NetworkConfig().getService()
            .getMovies()
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    onFinishedListener.onResultFail("Failed To Get Data")
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    onFinishedListener.onResultSuccess(response.body()?.results as List<MovieResponse>)
                }
            })
    }
}