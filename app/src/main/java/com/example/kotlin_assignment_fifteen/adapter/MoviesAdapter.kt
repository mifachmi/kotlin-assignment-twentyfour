package com.example.kotlin_assignment_fifteen.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin_assignment_fifteen.data.ResultsItem
import com.example.kotlin_assignment_fifteen.databinding.RowMovieItemsBinding

class MoviesAdapter(val dataMovie: List<ResultsItem>?) : RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    inner class MovieHolder(private val binding: RowMovieItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: ResultsItem) {
            with(binding){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + movies.posterPath)
                    .apply(RequestOptions().override(200, 200))
                    .into(ivPoster)

                tvTitle.text = movies.title
                tvGenre.text = movies.genreIds.toString()
                tvRating.text = movies.voteAverage.toString()
                rbMovie.rating = movies.voteAverage!!.toFloat()

                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, movies.title, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val viewHolder = RowMovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(dataMovie?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return dataMovie!!.size
    }
}