package com.example.pnntest.ViewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pnntest.R

class CastAdapter(val actors: ArrayList<String>) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    inner class CastViewHolder(val layout: LinearLayout) : RecyclerView.ViewHolder(layout) {
        fun bind(actor: String) {
            layout.findViewById<TextView>(R.id.actorNameTV).text = actor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layout: LinearLayout = LayoutInflater.from(parent.context).inflate(R.layout.cast_item, parent, false) as LinearLayout
        return CastViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(actors.get(position))
    }

    override fun getItemCount() = actors.size
}