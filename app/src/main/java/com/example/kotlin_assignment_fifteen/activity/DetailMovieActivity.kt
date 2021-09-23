package com.example.kotlin_assignment_fifteen.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin_assignment_fifteen.R
import com.example.kotlin_assignment_fifteen.data.ResultsItem
import com.example.kotlin_assignment_fifteen.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val _movies = intent.getParcelableExtra<ResultsItem>(EXTRA_MOVIES) as ResultsItem
        binding.apply {
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500/" + _movies.posterPath)
                .apply(RequestOptions().override(200, 200))
                .into(ivPosterHeader)
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500/" + _movies.posterPath)
                .apply(RequestOptions().override(200, 200))
                .into(ivPosterDetail)
            tvTitleDetail.text = _movies.title
            tvOverviewDetail.text = _movies.overview
            tvReleaseDate.text = _movies.releaseDate
            tvPopularity.text = _movies.popularity.toString()
            tvRatingDetail.text = _movies.voteAverage.toString()
            rbMovieDetail.rating = _movies.voteAverage!!.toFloat()
            tvJumlahUsers.text = _movies.voteCount.toString()
        }
    }

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }
}