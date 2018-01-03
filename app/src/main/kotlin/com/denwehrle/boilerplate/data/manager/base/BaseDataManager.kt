package com.denwehrle.boilerplate.data.manager.base

import com.denwehrle.boilerplate.data.local.helper.DatabaseHelper
import com.denwehrle.boilerplate.data.local.helper.PreferenceHelper

/**
 * @author Dennis Wehrle
 */
abstract class BaseDataManager(
        private val preferenceHelper: PreferenceHelper,
        private val databaseHelper: DatabaseHelper
) {

    /********* Preferences *********/

    fun clearPreferences() {
        preferenceHelper.clear()
    }

    fun isWelcomeDone() = preferenceHelper.welcomeDone

    fun setWelcomeDone() {
        preferenceHelper.welcomeDone = true
    }
}