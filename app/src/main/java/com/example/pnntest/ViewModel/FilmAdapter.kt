package com.example.pnntest.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.pnntest.Model.Film
import com.example.pnntest.R

class FilmAdapter(val onFilmClickListener: OnFilmClickListener) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layout: ConstraintLayout = LayoutInflater.from(parent.context).inflate(R.layout.film, parent, false) as ConstraintLayout
        return FilmViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getFilmByPosition(position))
    }

    override fun getItemCount() = films.size


    inner class FilmViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {

        fun bind(film: Film) {
            layout.findViewById<ImageView>(R.id.imgView).setImageBitmap(film.poster)
            layout.findViewById<TextView>(R.id.genreTV).setText(film.genre)
            layout.findViewById<TextView>(R.id.titleTV).setText(film.title)
            layout.findViewById<TextView>(R.id.directorTV).setText(film.director)
            layout.findViewById<TextView>(R.id.imbdTV).setText(film.imdbRating)
            layout.setOnClickListener { v: View ->
                run {
                    onFilmClickListener.onFilmClick(layoutPosition)
                }
            }
        }
    }

    interface OnFilmClickListener {
        fun onFilmClick(position: Int)
    }


    companion object {
        private val filmVM :FilmViewModel = FilmViewModel()
        private val films = filmVM.getFilmsArray()

        fun getFilmByPosition(position: Int): Film {
            return films[position]
        }
    }
}