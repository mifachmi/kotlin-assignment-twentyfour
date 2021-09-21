package com.example.kotlin_assignment_fifteen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
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
                    .load(movies.posterPath)
                    .apply(RequestOptions().override(200, 200))
                    .into(ivPoster)

                tvTitle.text = movies.title
                tvGenre.text = movies.genreIds.toString()
                tvRating.text = movies.voteAverage.toString()
                rbMovie.rating = movies.voteAverage!!.toFloat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.MovieHolder {
        val viewHolder = RowMovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieHolder, position: Int) {
        holder.bind(dataMovie?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return dataMovie!!.size
    }
}