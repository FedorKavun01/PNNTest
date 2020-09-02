package com.example.pnntest

import com.example.pnntest.Model.FilmRepository
import com.example.pnntest.Model.NetworkService
import kotlinx.coroutines.runBlocking

fun main() {

    var filmRepository = FilmRepository(NetworkService.getService())

    runBlocking {
        var films = filmRepository.getFilms()!!
        for (film in films) {
            println(film)
        }
        println(films.size)
    }
}