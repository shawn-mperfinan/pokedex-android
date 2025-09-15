package dev.mperfinan.pokedex

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics

class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val crashlytics = FirebaseCrashlytics.getInstance()

        // Tag which flavor is running
        crashlytics.setCustomKey("flavor", BuildConfig.FLAVOR)

        // Optional: also tag version
        crashlytics.setCustomKey("versionName", BuildConfig.VERSION_NAME)
        crashlytics.setCustomKey("versionCode", BuildConfig.VERSION_CODE)
    }
}
