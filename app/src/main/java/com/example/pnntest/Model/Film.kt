package com.example.pnntest.Model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

data class Film(@SerializedName("Genre") val genre: String, @SerializedName("Director") val director: String,
                @SerializedName("Poster") val posterURL: String, @SerializedName("Title") val title: String,
                val imdbRating: String) {
    var poster: Bitmap? = null

    fun getPoster() {
        if (posterURL!= "N/A") {
            poster = runBlocking {
                Picasso.get().load(posterURL).get()
            }
        }
    }


}
