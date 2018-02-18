package com.denwehrle.boilerplate.data.local.helper

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 * General Preferences Helper class, used for storing values using the Preference API
 */
@Singleton
class PreferenceHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_FILE_NAME = "preferences"

        private const val PREF_KEY_LAST_CACHE = "last_cache"
        private const val PREF_KEY_WELCOME_DONE = "welcome_done"
    }

    private val preferences by lazy {
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = preferences.getLong(PREF_KEY_LAST_CACHE, 0)
        set(value) = preferences.edit().putLong(PREF_KEY_LAST_CACHE, value).apply()

    /**
     * Store and retrieve if the welcome is done
     */
    var welcomeDone: Boolean
        get() = preferences.getBoolean(PREF_KEY_WELCOME_DONE, false)
        set(value) = preferences.edit().putBoolean(PREF_KEY_WELCOME_DONE, value).apply()
}