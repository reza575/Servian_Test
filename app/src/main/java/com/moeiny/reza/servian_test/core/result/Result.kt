package com.moeiny.reza.servian_test.core.result

import androidx.annotation.StringRes
import com.moeiny.reza.servian_test.R

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable?, @StringRes private val messageId: Int? = null) :
        Result<Nothing>() {
        fun messageId(): Int = messageId ?: R.string.unknown_error
    }

    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable=$throwable]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> =
    when (this) {
        is Result.Success -> {
            Result.Success(transform(data))
        }
        is Result.Error -> {
            this
        }
        is Result.Loading -> {
            this
        }
    }