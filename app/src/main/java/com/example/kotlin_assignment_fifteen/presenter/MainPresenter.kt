package com.example.kotlin_assignment_fifteen.presenter

import com.example.kotlin_assignment_fifteen.model.interactor.MainInteractor
import com.example.kotlin_assignment_fifteen.model.response.MovieResponse
import com.example.kotlin_assignment_fifteen.ui.mainactivity.MainActivity

class MainPresenter(
    private var mainView: MainActivity.MainView,
    private val mainInteractor: MainInteractor
) : MainInteractor.OnFinishedListener {

    fun getData() {

    }

    override fun onResultSuccess(arrUpdates: List<MovieResponse>) {
        TODO("Not yet implemented")
    }

    override fun onResultFail(strError: String) {
        TODO("Not yet implemented")
    }

    fun onDestroy() {

    }

}