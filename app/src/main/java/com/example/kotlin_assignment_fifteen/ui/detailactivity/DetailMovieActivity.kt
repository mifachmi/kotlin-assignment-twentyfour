package com.example.kotlin_assignment_fifteen.ui.detailactivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin_assignment_fifteen.databinding.ActivityDetailMovieBinding
import com.example.kotlin_assignment_fifteen.model.network.NetworkConfig
import com.example.kotlin_assignment_fifteen.model.response.DetailMovieResponse
import com.example.kotlin_assignment_fifteen.model.response.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movies = intent.getParcelableExtra<ResultsItem>(EXTRA_MOVIES) as ResultsItem
        getDetailMovie(movies.id.toString())
    }

    private fun getDetailMovie(idMovie: String) {
        NetworkConfig().getService().getDetailMovie(idMovie)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    if(response.isSuccessful) {
                        binding.apply {
                            Glide.with(applicationContext)
                                .load("https://image.tmdb.org/t/p/w500/" + response.body()?.posterPath)
                                .apply(RequestOptions().override(200, 200))
                                .into(ivPosterHeader)
                            Glide.with(applicationContext)
                                .load("https://image.tmdb.org/t/p/w500/" + response.body()?.posterPath)
                                .apply(RequestOptions().override(200, 200))
                                .into(ivPosterDetail)
                            tvTitleDetail.text = response.body()?.title
                            tvOverviewDetail.text = response.body()?.overview
                            tvReleaseDate.text = response.body()?.releaseDate
                            tvPopularity.text = response.body()?.popularity.toString()
                            tvRatingDetail.text = response.body()?.voteAverage.toString()
                            rbMovieDetail.rating = response.body()?.voteAverage!!.toFloat()
                            tvJumlahUsers.text = response.body()?.voteCount.toString()
                        }
                    } else {
                        Log.d("TAG ERROR", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    Log.d("TAG", t.localizedMessage)
                }
            })
    }

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }
}