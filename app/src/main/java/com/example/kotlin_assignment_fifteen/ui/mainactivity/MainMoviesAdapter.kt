package com.example.kotlin_assignment_fifteen.ui.mainactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin_assignment_fifteen.databinding.RowMovieItemsBinding
import com.example.kotlin_assignment_fifteen.model.response.ResultsItem

class MainMoviesAdapter(private val dataMovie: List<ResultsItem>?) : RecyclerView.Adapter<MainMoviesAdapter.MovieHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

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
                    onItemClickCallback.onItemClicked(dataMovie!![bindingAdapterPosition])
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

    interface OnItemClickCallback {
        fun onItemClicked(data: ResultsItem)
    }
}