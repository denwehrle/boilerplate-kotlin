package com.denwehrle.boilerplate.data.manager.base

import com.denwehrle.boilerplate.data.local.helper.PreferenceHelper

/**
 * The [BaseDataManager] is the single point of communication between the different layers.
 *
 * @author Dennis Wehrle
 */
abstract class BaseDataManager(private val preferenceHelper: PreferenceHelper) {


    /********* Preferences *********/

    fun isWelcomeDone() = preferenceHelper.welcomeDone

    fun setWelcomeDone() {
        preferenceHelper.welcomeDone = true
    }
}