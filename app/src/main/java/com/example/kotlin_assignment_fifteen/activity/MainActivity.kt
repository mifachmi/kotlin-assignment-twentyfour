package com.example.kotlin_assignment_fifteen.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.pbMain.visibility = View.VISIBLE

        NetworkConfig().getService()
            .getMovies()
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("error", t.localizedMessage)
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    binding.rvMovie.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvMovie.adapter = MoviesAdapter(response.body()!!.results)
                    binding.pbMain.visibility = View.GONE
                }
            })
    }
}
