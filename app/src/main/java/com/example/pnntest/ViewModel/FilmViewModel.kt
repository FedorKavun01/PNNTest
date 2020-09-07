package com.example.pnntest.ViewModel

import com.example.pnntest.Model.Film
import com.example.pnntest.Model.FilmRepository
import com.example.pnntest.Model.NetworkService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FilmViewModel {
    private var films: Array<Film>? = null
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    val repository = FilmRepository(NetworkService.getService())

    private fun fillFilmsList() {
        runBlocking {
            scope.async {
                films = repository.getFilms()
            }.await()
        }
        if (films == null) {
            films = arrayOf()
        }
    }

    fun getFilmsArray(): Array<Film> {
        if (films == null) {
            fillFilmsList()
        }
        return films!!
    }

    fun getFilmById(id: Int) = films!![id]
}