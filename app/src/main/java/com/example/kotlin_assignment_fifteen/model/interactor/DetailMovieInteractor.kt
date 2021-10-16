package com.example.kotlin_assignment_fifteen.model.interactor

import com.example.kotlin_assignment_fifteen.model.network.NetworkConfig
import com.example.kotlin_assignment_fifteen.model.response.DetailMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieInteractor {

    interface OnFinishedListenerDetailMovie {
        fun onResultSuccess(dataMovie: DetailMovieResponse)
        fun onResultFail(strError: String)
    }

    fun getDetailMovie(
        idMovie: String,
        onFinishedListenerDetailMovie: OnFinishedListenerDetailMovie
    ) {
        NetworkConfig().getService().getDetailMovie(idMovie)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    onFinishedListenerDetailMovie.onResultSuccess(response.body() as DetailMovieResponse)
                }

                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    onFinishedListenerDetailMovie.onResultFail("Failed To Get Data")

                }
            })
    }
}