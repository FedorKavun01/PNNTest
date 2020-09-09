package com.example.pnntest.Model

import android.graphics.Bitmap
import android.media.Rating
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking
import java.io.Writer

data class Film(@SerializedName("Genre") val genre: String,
                @SerializedName("Director") val director: String,
                @SerializedName("Poster")private val posterURL: String,
                @SerializedName("Title") val title: String,
                @SerializedName("imdbRating")val imdbRating: String,
                @SerializedName("Ratings")private val ratings : Array<Rating>,
                @SerializedName("Released") val released: String,
                @SerializedName("Runtime") val runtime: String,
                @SerializedName("Plot") val story: String,
                @SerializedName("Writer")private val writers: String,
                @SerializedName("Actors") private val actors: String) {
    var poster: Bitmap? = null
    var writersList: ArrayList<Writer>? = null
    var actorsList: ArrayList<String>? = null
    var ratingsList: ArrayList<Rating>? = null

    fun prepareFilm() {
        if (posterURL!= "N/A") {
            poster = runBlocking {
                Picasso.get().load(posterURL).get()
            }
        }
        writersList = ArrayList()
        actorsList = ArrayList(actors.split(", "))
        val writersStrs = writers.split(", ")
        var name = ""
        var role = ""
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

        ratingsList = ArrayList()
        if (ratings != null) {
            for (r in ratings) {
                var source: String = ""
                var value: String = ""
                if (r.source.equals("Internet Movie Database")) {
                    source = "IMBD"
                } else {
                    source = r.source
                }
                value = r.value
                ratingsList!!.add(Rating(source, value))
            }
        }
    }

    data class Rating(@SerializedName("Source") var source: String, @SerializedName("Value") val value: String)

    data class Writer(val name: String, val role: String)
}
