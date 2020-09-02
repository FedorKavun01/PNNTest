package com.example.pnntest.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pnntest.Model.Film
import com.example.pnntest.R
import com.example.pnntest.ViewModel.FilmViewModel

class MainActivity : AppCompatActivity() {

    lateinit var hScroll: LinearLayout
    val filmViewModel = FilmViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hScroll = findViewById(R.id.hScroll)
        fillHScroll()
    }

    fun fillHScroll() {
        var films = filmViewModel.getFilmsArray()
        if (!films.isEmpty()) {
            for (film in films) {
                val view = createView(film)
                hScroll.addView(view)
            }
        } else {
            Toast.makeText(this, "List is empty", Toast.LENGTH_LONG).show()
        }
    }

    fun createView(film: Film): View {
        val lInflater = layoutInflater
        val view = lInflater.inflate(R.layout.film, null)
        val poster = film.poster
        view.findViewById<ImageView>(R.id.imgView).setImageBitmap(poster)
        view.findViewById<TextView>(R.id.genreTV).setText(film.genre)
        view.findViewById<TextView>(R.id.titleTV).setText(film.title)
        view.findViewById<TextView>(R.id.directorTV).setText(film.director)
        val imbdTV = view.findViewById<TextView>(R.id.imbdTV)
        var imbdText = "<font color=#dade14>${film.imdbRating}</font>"
        imbdTV.setText("${imbdTV.text} ${Html.fromHtml(imbdText, Html.FROM_HTML_MODE_LEGACY)}")
        return view
    }
}