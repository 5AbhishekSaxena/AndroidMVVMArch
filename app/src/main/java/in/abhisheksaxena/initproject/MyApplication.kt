package `in`.abhisheksaxena.initproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


/**
 * @author Abhishek Saxena
 * @since 09-09-2020 11:03
 */

@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}