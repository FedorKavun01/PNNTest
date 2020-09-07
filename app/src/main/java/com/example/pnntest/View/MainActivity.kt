package com.example.pnntest.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pnntest.Model.Film
import com.example.pnntest.R
import com.example.pnntest.ViewModel.FilmViewModel

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var hScroll: LinearLayout

    companion object {
        val filmViewModel = FilmViewModel()
    }

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

    // TODO: 04.09.2020 Change this list to RecyclerView (not require)
    
    fun createView(film: Film): View {
        val lInflater = layoutInflater
        val view = lInflater.inflate(R.layout.film, null)
        view.findViewById<ImageView>(R.id.imgView).setImageBitmap(film.poster)
        view.findViewById<TextView>(R.id.genreTV).setText(film.genre)
        view.findViewById<TextView>(R.id.titleTV).setText(film.title)
        view.findViewById<TextView>(R.id.directorTV).setText(film.director)
        val imbdTV = view.findViewById<TextView>(R.id.imbdTV)
        imbdTV.setText(" ${film.imdbRating}")
        view.id = film.id
        view.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        var intent = Intent(this, FilmActivity::class.java)
        intent.putExtra("id", v!!.id)
        startActivity(intent)
    }
}