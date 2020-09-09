package `in`.abhisheksaxena.initproject.utils

import androidx.lifecycle.Observer


/**
 * @author Abhishek Saxena
 * @since 09-09-2020 10:56
 */

class Event<T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T?{
        return if(hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T? = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit): Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let{
            onEventUnhandledContent(it)
        }
    }
}