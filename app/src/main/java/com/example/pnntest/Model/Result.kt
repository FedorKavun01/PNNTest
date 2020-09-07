package com.example.pnntest.Model

import java.lang.Exception

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T) : Result<T>()

    // TODO: 03.09.2020 Read about Nothing class (not require)
    data class Error(val exception: Exception): Result<Nothing>()
}