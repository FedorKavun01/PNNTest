package com.example.pnntest.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pnntest.Model.Film
import com.example.pnntest.R
import com.example.pnntest.ViewModel.FilmAdapter
import com.example.pnntest.ViewModel.FilmViewModel
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), FilmAdapter.OnFilmClickListener {

    lateinit var filmsRV: RecyclerView

    companion object {
        val POSITION_TAG: String = "Position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filmsRV = findViewById<RecyclerView>(R.id.filmsRV).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = FilmAdapter(this@MainActivity)
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(filmsRV)
    }

    override fun onFilmClick(position: Int) {
        var intent = Intent(this, FilmActivity::class.java)
        intent.putExtra(POSITION_TAG, position)
        startActivity(intent)
    }
}