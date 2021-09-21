package com.example.kotlin_assignment_fifteen.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_assignment_fifteen.adapter.MoviesAdapter
import com.example.kotlin_assignment_fifteen.data.MovieResponse
import com.example.kotlin_assignment_fifteen.data.ResultsItem
import com.example.kotlin_assignment_fifteen.databinding.ActivityMainBinding
import com.example.kotlin_assignment_fifteen.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NetworkConfig().getService()
            .getMovies()
            .enqueue(object : Callback<List<MovieResponse>> {

                override fun onFailure(call: Call<List<MovieResponse>>, t: Throwable) {
                    Log.d("error", "error gan")
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<MovieResponse>>,
                    response: Response<List<MovieResponse>>
                ) {
                    binding.rvMovie.adapter = MoviesAdapter(response.body() as List<ResultsItem>)
                }
            })
    }
}
