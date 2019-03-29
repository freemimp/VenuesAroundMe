package com.freemimp.android.aroundme.domain

sealed class Maybe<T> {
    class Success<T>(val data: T) : Maybe<T>()

    class Error<T>(val error: Throwable) : Maybe<T>()
}