package com.example.pnntest.Model

import android.util.Log

class FilmRepository(val api: APIService): BaseRepository() {

    val TAG = "FilmRepository"

    suspend fun getFilms(): Array<Film>? {
        val instance = safeApiCall(call = {api.getFilms().await()}, "")

        return if (instance != null) {
//            Log.d(TAG, "getFilm: successful")
            for (film in instance) {
                film.prepareFilm()
            }
            instance
        } else {
//            Log.d(TAG, "getFilm: error")
            null
        }
    }
}