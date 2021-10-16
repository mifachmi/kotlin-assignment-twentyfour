package com.example.kotlin_assignment_fifteen.ui.detailactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin_assignment_fifteen.databinding.ActivityDetailMovieBinding
import com.example.kotlin_assignment_fifteen.model.interactor.DetailMovieInteractor
import com.example.kotlin_assignment_fifteen.model.response.DetailMovieResponse
import com.example.kotlin_assignment_fifteen.model.response.ResultsItem
import com.example.kotlin_assignment_fifteen.presenter.DetailMoviePresenter
import com.example.kotlin_assignment_fifteen.utils.EXTRA_MOVIES
import com.example.kotlin_assignment_fifteen.utils.showToast

class DetailMovieActivity : AppCompatActivity(), DetailMovieView {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var detailMoviePresenter: DetailMoviePresenter
    private lateinit var movies: ResultsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movies = intent.getParcelableExtra(EXTRA_MOVIES)!!
        detailMoviePresenter = DetailMoviePresenter(this, DetailMovieInteractor())
        detailMoviePresenter.getDataDetailMovie(movies.id.toString())

    }

    override fun setDetailMovie(dataMovie: DetailMovieResponse) {
        binding.apply {
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500/" + dataMovie.posterPath)
                .apply(RequestOptions().override(200, 200))
                .into(ivPosterHeader)
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500/" + dataMovie.posterPath)
                .apply(RequestOptions().override(200, 200))
                .into(ivPosterDetail)
            tvTitleDetail.text = dataMovie.title
            tvOverviewDetail.text = dataMovie.overview
            tvReleaseDate.text = dataMovie.releaseDate
            tvPopularity.text = dataMovie.popularity.toString()
            tvRatingDetail.text = dataMovie.voteAverage.toString()
            rbMovieDetail.rating = dataMovie.voteAverage!!.toFloat()
            tvJumlahUsers.text = dataMovie.voteCount.toString()
        }
    }

    override fun getDataFailed(strError: String) {
        showToast(this, strError, Toast.LENGTH_SHORT)
    }
}