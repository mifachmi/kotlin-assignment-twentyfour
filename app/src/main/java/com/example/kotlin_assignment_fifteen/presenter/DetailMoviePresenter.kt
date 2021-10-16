package com.example.kotlin_assignment_fifteen.presenter

import com.example.kotlin_assignment_fifteen.model.interactor.DetailMovieInteractor
import com.example.kotlin_assignment_fifteen.model.response.DetailMovieResponse
import com.example.kotlin_assignment_fifteen.ui.detailactivity.DetailMovieView

class DetailMoviePresenter(
    private var detailMovieView: DetailMovieView,
    private val detailMovieInteractor: DetailMovieInteractor
) : DetailMovieInteractor.OnFinishedListenerDetailMovie{

    fun getDataDetailMovie(idMovie: String) {
        detailMovieInteractor.getDetailMovie(idMovie,this)
    }

    override fun onResultSuccess(dataMovie: DetailMovieResponse) {
        detailMovieView.setDetailMovie(dataMovie)
    }

    override fun onResultFail(strError: String) {
        detailMovieView.getDataFailed(strError)
    }

}