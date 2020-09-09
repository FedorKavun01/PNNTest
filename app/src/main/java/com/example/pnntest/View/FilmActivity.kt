
package com.example.pnntest.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginLeft
import com.example.pnntest.Model.Film
import com.example.pnntest.R
import com.example.pnntest.ViewModel.FilmAdapter

class FilmActivity : AppCompatActivity() {

    lateinit var filmIV: ImageView
    lateinit var genreTV: TextView
    lateinit var titleTV: TextView
    lateinit var ratingLL: LinearLayout
    lateinit var releasedTV: TextView
    lateinit var runtimeTV: TextView
    lateinit var directorTV: TextView
    lateinit var storyTV: TextView
    lateinit var writersLL: LinearLayout
    lateinit var castLL: LinearLayout

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
        ratingLL = findViewById(R.id.ratingLL)
        releasedTV = findViewById(R.id.releasedTV)
        runtimeTV = findViewById(R.id.runtimeTV)
        directorTV = findViewById(R.id.directorTV)
        storyTV = findViewById(R.id.storyTV)
        writersLL = findViewById(R.id.writersLL)
        castLL = findViewById(R.id.castLL)
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
        fillAdvanceView(ratingLL, R.layout.rating_item, R.id.ratingNameTV, R.id.ratingMarkTV, film.ratings)
        releasedTV.setText(film.released)
        runtimeTV.setText(film.runtime)
        directorTV.setText(film.director)
        storyTV.setText(film.story)
        fillAdvanceView(writersLL, R.layout.writers_item, R.id.writerRoleTV, R.id.writerNameTV, film.writersList!!)
        fillAdvanceView(castLL, R.layout.cast_item, R.id.actorIV, R.id.actorNameTV, film.actorsList!!)


    }

    private fun fillAdvanceView(layout: LinearLayout, inflateRatingLayoutID: Int, sourceTVID: Int, valueTVID: Int, data: Array<Film.Rating>) {
        if(data != null) {
            for (rating in data) {
                val lInflater = layoutInflater
                val view = lInflater.inflate(inflateRatingLayoutID, null, true)
                var source = ""
                if (rating.source.equals("Internet Movie Database")) {
                    source = "IMBD"
                } else {
                    source = rating.source
                }
                view.findViewById<TextView>(sourceTVID).text = source
                view.findViewById<TextView>(valueTVID).text = rating.value
                val lParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                lParams.rightMargin = 25
                layout.addView(view, lParams)
            }
        }

    }

    private fun fillAdvanceView(layout: LinearLayout, inflateWritersLayoutID: Int, roleTVID: Int, nameTVID: Int, data: ArrayList<Film.Writer>) {
        for (writer in data) {
            val lInflater = layoutInflater
            val view = lInflater.inflate(inflateWritersLayoutID, null, true)
            view.findViewById<TextView>(roleTVID).text = writer.role
            view.findViewById<TextView>(nameTVID).text = writer.name
            val lParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            lParams.rightMargin = 25
            layout.addView(view, lParams)
        }
    }

    private fun fillAdvanceView(layout: LinearLayout, inflateCastLayoutID: Int, imgID: Int, dataTVID: Int, data: List<String>) {
        for (actorName in data) {
            val lInflater = layoutInflater
            val view = lInflater.inflate(inflateCastLayoutID, null)
            view.findViewById<TextView>(dataTVID).text = actorName
            val lParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            lParams.rightMargin = 25
            layout.addView(view, lParams)
        }
    }
}