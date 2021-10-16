package com.example.kotlin_assignment_fifteen.ui.mainactivity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_assignment_fifteen.databinding.ActivityMainBinding
import com.example.kotlin_assignment_fifteen.model.interactor.MainInteractor
import com.example.kotlin_assignment_fifteen.model.response.MovieResponse
import com.example.kotlin_assignment_fifteen.model.response.ResultsItem
import com.example.kotlin_assignment_fifteen.presenter.MainPresenter
import com.example.kotlin_assignment_fifteen.utils.showToast

class MainActivity : AppCompatActivity(), MainView{

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainPresenter = MainPresenter(this, MainInteractor())
        binding.pbMain.visibility = View.VISIBLE
        mainPresenter.getData()
    }

    override fun setDataToRecycleView(dataMovie: List<MovieResponse>) {
        binding.rvMovie.layoutManager = LinearLayoutManager(this@MainActivity)
        val listMovie = MainMoviesAdapter(dataMovie as List<ResultsItem>)
        binding.rvMovie.adapter = listMovie

        listMovie.setOnItemClickCallback(object : MainMoviesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ResultsItem) {
                showToast(this@MainActivity, data.title.toString(), Toast.LENGTH_SHORT)
                mainPresenter.onItemClick(this@MainActivity, data)
            }

        })
    }

    override fun hideProgressBar() {
        binding.pbMain.visibility = View.GONE
    }

    override fun showProgressBar() {
        binding.pbMain.visibility = View.VISIBLE
    }

    override fun getDataFailed(strError: String) {
        showToast(this, strError, Toast.LENGTH_SHORT)
    }

}
