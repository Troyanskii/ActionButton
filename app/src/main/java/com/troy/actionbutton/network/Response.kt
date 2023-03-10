package com.troy.actionbutton.network

sealed class Response<out T : Any> {
    data class Success<out T : Any> (val result : T) : Response<T>()
    data class Error (val message : String) : Response<Nothing>()
}