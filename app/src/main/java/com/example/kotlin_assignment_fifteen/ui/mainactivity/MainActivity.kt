package com.example.kotlin_assignment_fifteen.ui.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_assignment_fifteen.databinding.ActivityMainBinding
import com.example.kotlin_assignment_fifteen.model.network.NetworkConfig
import com.example.kotlin_assignment_fifteen.model.response.MovieResponse
import com.example.kotlin_assignment_fifteen.model.response.ResultsItem
import com.example.kotlin_assignment_fifteen.ui.detailactivity.DetailMovieActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pbMain.visibility = View.VISIBLE

        NetworkConfig().getService()
            .getMovies()
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    binding.rvMovie.layoutManager = LinearLayoutManager(this@MainActivity)
                    val listMovie = MainMoviesAdapter(response.body()!!.results)
                    binding.rvMovie.adapter = listMovie
                    binding.pbMain.visibility = View.GONE

                    listMovie.setOnItemClickCallback(object : MainMoviesAdapter.OnItemClickCallback {
                        override fun onItemClicked(data: ResultsItem) {
                            val intentToDetail = Intent(this@MainActivity, DetailMovieActivity::class.java)
                            intentToDetail.putExtra(DetailMovieActivity.EXTRA_MOVIES, data)
                            startActivity(intentToDetail)
                        }

                    })
                }
            })
    }

    interface MainView {
        fun setDataToRecycleView(dataMovie: List<MovieResponse>)
    }
}
