package com.example.kotlin_assignment_fifteen.utils

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, strError: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, strError, length).show()
}

const val EXTRA_MOVIES = "extra_movies"
