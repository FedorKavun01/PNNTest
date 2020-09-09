package com.example.pnntest.ViewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pnntest.Model.Film
import com.example.pnntest.R

class WriterAdapter(val writers: ArrayList<Film.Writer>) : RecyclerView.Adapter<WriterAdapter.WriterViewHolder>() {
    inner class WriterViewHolder(val layout: LinearLayout) : RecyclerView.ViewHolder(layout) {
        fun bind(writer: Film.Writer) {
            layout.findViewById<TextView>(R.id.writerRoleTV).text = writer.role
            layout.findViewById<TextView>(R.id.writerNameTV).text = writer.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriterViewHolder {
        val layout: LinearLayout = LayoutInflater.from(parent.context).inflate(R.layout.writers_item, parent, false) as LinearLayout
        return WriterViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WriterViewHolder, position: Int) {
        holder.bind(writers.get(position))
    }

    override fun getItemCount() = writers.size
}