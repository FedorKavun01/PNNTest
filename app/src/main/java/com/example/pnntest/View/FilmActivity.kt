
package com.example.pnntest.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pnntest.Model.Film
import com.example.pnntest.R
import com.example.pnntest.ViewModel.CastAdapter
import com.example.pnntest.ViewModel.FilmAdapter
import com.example.pnntest.ViewModel.RatingAdapter
import com.example.pnntest.ViewModel.WriterAdapter

class FilmActivity : AppCompatActivity() {

    lateinit var filmIV: ImageView
    lateinit var genreTV: TextView
    lateinit var titleTV: TextView
    lateinit var ratingRV: RecyclerView
    lateinit var releasedTV: TextView
    lateinit var runtimeTV: TextView
    lateinit var directorTV: TextView
    lateinit var storyTV: TextView
    lateinit var writersRV: RecyclerView
    lateinit var castRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        initFields()
        val film = getFilm()
        showFilm(film)
    }

    fun initFields(): Unit {
        filmIV = findViewById(R.id.filmIV)
        genreTV = findViewById(R.id.genreTV)
        titleTV = findViewById(R.id.titleTV)
        ratingRV = findViewById(R.id.ratingRV)
        releasedTV = findViewById(R.id.releasedTV)
        runtimeTV = findViewById(R.id.runtimeTV)
        directorTV = findViewById(R.id.directorTV)
        storyTV = findViewById(R.id.storyTV)
        writersRV = findViewById(R.id.writersRV)
        castRV = findViewById(R.id.castRV)
    }

    fun getFilm(): Film {
        val position: Int = intent.extras!!.get(MainActivity.POSITION_TAG) as Int
        val film = FilmAdapter.getFilmByPosition(position)
        return film
    }

    fun showFilm(film: Film) {
        filmIV.setImageBitmap(film.poster)
        genreTV.setText(film.genre)
        titleTV.setText(film.title)
        with(ratingRV) {
            layoutManager = LinearLayoutManager(this@FilmActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = RatingAdapter(film.ratingsList!!)
        }
        releasedTV.setText(film.released)
        runtimeTV.setText(film.runtime)
        directorTV.setText(film.director)
        storyTV.setText(film.story)
        with(writersRV) {
            layoutManager = LinearLayoutManager(this@FilmActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = WriterAdapter(film.writersList!!)
        }
        with(castRV) {
            layoutManager = LinearLayoutManager(this@FilmActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = CastAdapter(film.actorsList!!)
        }
    }
}