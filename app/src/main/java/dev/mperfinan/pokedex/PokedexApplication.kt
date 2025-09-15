package dev.mperfinan.pokedex

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.mperfinan.pokedex.util.Constants.APP_FLAVOR
import dev.mperfinan.pokedex.util.Constants.APP_VERSION_CODE
import dev.mperfinan.pokedex.util.Constants.APP_VERSION_NAME

class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initializeFirebaseCrashlytics()
    }

    private fun initializeFirebaseCrashlytics() {
        // Enable Firebase Crashlytics
        FirebaseCrashlytics.getInstance().apply {
            setCustomKey(APP_FLAVOR, BuildConfig.FLAVOR)
            setCustomKey(APP_VERSION_NAME, BuildConfig.VERSION_NAME)
            setCustomKey(APP_VERSION_CODE, BuildConfig.VERSION_CODE)
        }
    }
}
