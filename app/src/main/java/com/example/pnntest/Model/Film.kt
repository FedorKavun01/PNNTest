package com.example.pnntest.Model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking

data class Film(@SerializedName("Genre") val genre: String, @SerializedName("Director") val director: String,
                @SerializedName("Poster") val posterURL: String, @SerializedName("Title") val title: String,
                val imdbRating: String, @SerializedName("Ratings") private val rating : Array<Rating>,
                @SerializedName("Released") val released: String, @SerializedName("Plot") val story: String,
                @SerializedName("Writer")private val writers: String, @SerializedName("Actors") private val actors: String) {
    var poster: Bitmap? = null
    var writersList: ArrayList<Writer>? = null
    var actorsList: List<String>? = null
    var id: Int = 0

    fun prepareFilm() {
        id = i++
        if (posterURL!= "N/A") {
            poster = runBlocking {
                Picasso.get().load(posterURL).get()
            }
        }
        writersList = ArrayList()
        actorsList = actors.split(", ")
        val writersStrs = writers.split(", ")
        var name: String = ""
        var role: String = ""
        for (writerStr in writersStrs) {
            if (writerStr.contains(" (")) {
                name = writerStr.split(" (").get(0)
                if(role == writerStr.split(" (").get(1).dropLast(1)) {
                    role = ""
                } else {
                    role = writerStr.split(" (").get(1).dropLast(1)
                }
            } else {
                name = writerStr
            }
            writersList!!.add(Writer(name, role))
        }

    }

    companion object {
        var i: Int = 0
    }

    data class Rating(@SerializedName("Source") val source: String, @SerializedName("Value") val value: String)

    data class Writer(val name: String, val role: String)
}
