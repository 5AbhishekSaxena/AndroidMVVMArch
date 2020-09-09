package `in`.abhisheksaxena.baaten.ui.base

import `in`.abhisheksaxena.baaten.MyApplication
import `in`.abhisheksaxena.baaten.utils.Event
import `in`.abhisheksaxena.baaten.utils.ToasterStyle
import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/**
 * @author Abhishek Saxena
 * @since 09-09-2020 10:13
 */

abstract class AbstractViewModel(application: Application) : AndroidViewModel(application) {

    private val _snackbarText: MutableLiveData<Event<String>> = MutableLiveData()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    private val _progressbarStatus: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val progressbarStatus: LiveData<Event<Boolean>> = _progressbarStatus

    private val _toasterText: MutableLiveData<Event<ToasterStyle>> = MutableLiveData()
    val toasterText: LiveData<Event<ToasterStyle>> = _toasterText

    private val resources = this.getApplication<MyApplication>().resources

    open fun showSnackbarMessage(
        @StringRes messageRes: Int
    ) {
        resources?.let { res ->
            setupSnackBarEvent(res.getString(messageRes))
        }
    }

    open fun showSnackbarMessage(
        @StringRes messageRes: Int, vararg formatArg: Any
    ) {
        resources?.let { res ->
            setupSnackBarEvent(res.getString(messageRes, formatArg))
        }
    }

    private fun setupSnackBarEvent(
        message: String
    ) {
        _snackbarText.value = Event(message)
    }

    fun updateProgressbarStatus(isVisible: Boolean) {
        _progressbarStatus.value = Event(isVisible)
    }

    fun displayToastMessage(messageRes: Int, type: ToasterStyle.Type, drawableRes: Int? = null){
        resources?.let{res ->
            displayToastMessage(res.getString(messageRes), type, drawableRes)
        }
    }

    protected fun displayToastMessage(message: String, type: ToasterStyle.Type, drawableRes: Int? = null){
        val toasterModel = ToasterStyle(message, type, drawableRes)
        _toasterText.value = Event(toasterModel)
    }
}