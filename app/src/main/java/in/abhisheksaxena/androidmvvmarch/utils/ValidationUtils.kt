package `in`.abhisheksaxena.androidmvvmarch.utils

import android.util.Patterns


/**
 * @author Abhishek Saxena
 * @since 09-09-2020 10:58
 */

object ValidationUtils {

    fun String.isEmailNotValid() = !Patterns.EMAIL_ADDRESS.matcher(this).matches()
}