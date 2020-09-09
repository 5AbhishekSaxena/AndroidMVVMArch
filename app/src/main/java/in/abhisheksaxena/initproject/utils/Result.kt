package `in`.abhisheksaxena.initproject.utils

import java.lang.Exception


/**
 * @author Abhishek Saxena
 * @since 09-09-2020 10:48
 */

sealed class Result<out R>{

    data class Success<out T>(val data : T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
    object Loading: Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data= $data]"
            is Error -> "Error[exception= $exception]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.isSuccessful
    get() = this is Result.Success && data != null