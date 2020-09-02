package com.example.pnntest.Model

class FilmRepository(val api: APIService): BaseRepository() {

    val TAG = "FilmRepository"

    suspend fun getFilms(): Array<Film>? {
        val instance = safeApiCall(call = {api.getFilm().await()}, "")

        return if (instance != null) {
//            Log.d(TAG, "getFilm: successful")
            instance
        } else {
//            Log.d(TAG, "getFilm: error")
            null
        }
    }
}