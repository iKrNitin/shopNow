package com.example.kroma.Data

sealed class ApiState {
    object Empty:ApiState()
    class Failure(val msg:Throwable):ApiState()
    class Success<T>(val data: T) : ApiState()
    object Loading:ApiState()
}