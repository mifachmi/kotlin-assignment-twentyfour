package com.example.kotlin_assignment_fifteen.presenter

import android.content.Intent
import com.example.kotlin_assignment_fifteen.model.interactor.MainInteractor
import com.example.kotlin_assignment_fifteen.model.response.MovieResponse
import com.example.kotlin_assignment_fifteen.model.response.ResultsItem
import com.example.kotlin_assignment_fifteen.ui.detailactivity.DetailMovieActivity
import com.example.kotlin_assignment_fifteen.ui.mainactivity.MainActivity
import com.example.kotlin_assignment_fifteen.ui.mainactivity.MainView
import com.example.kotlin_assignment_fifteen.utils.EXTRA_MOVIES

class MainPresenter(
    private var mainView: MainView?,
    private val mainInteractor: MainInteractor?
) : MainInteractor.OnFinishedListener {

    fun getData() {
        mainView?.showProgressBar()
        mainInteractor?.requestGetDataAPI(this)
    }

    override fun onResultSuccess(dataMovie: List<MovieResponse>) {
        mainView?.hideProgressBar()
        mainView?.setDataToRecycleView(dataMovie)
    }

    override fun onResultFail(strError: String) {
        mainView?.hideProgressBar()
        mainView?.getDataFailed(strError)
    }

    fun onItemClick(activity: MainActivity, adapterPosition: ResultsItem) {
        val intentToDetail = Intent(activity, DetailMovieActivity::class.java)
        intentToDetail.putExtra(EXTRA_MOVIES, adapterPosition)
        activity.startActivity(intentToDetail)
    }
}