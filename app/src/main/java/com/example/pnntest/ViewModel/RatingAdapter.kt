package com.example.pnntest.ViewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pnntest.Model.Film
import com.example.pnntest.R
import kotlinx.android.synthetic.main.rating_item.view.*

class RatingAdapter(val ratings: ArrayList<Film.Rating>) : RecyclerView.Adapter<RatingAdapter.RatingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.rating_item, parent, false) as LinearLayout
        return RatingViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bind(ratings.get(position))
    }

    override fun getItemCount() = ratings.size

    inner class RatingViewHolder(val layout: LinearLayout) : RecyclerView.ViewHolder(layout) {
        fun bind(rating: Film.Rating) {
            layout.findViewById<TextView>(R.id.ratingNameTV).text = rating.source
            layout.findViewById<TextView>(R.id.ratingMarkTV).text = rating.value
        }
    }
}