package com.denwehrle.boilerplate.test

import com.denwehrle.boilerplate.data.local.helper.PreferenceHelper
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * @author Dennis Wehrle
 */
@RunWith(RobolectricTestRunner::class)
class PreferenceHelperTest {

    private lateinit var preferenceHelper: PreferenceHelper

    @Before
    fun setUp() {
        preferenceHelper = PreferenceHelper(RuntimeEnvironment.application)
    }

    @Test
    fun setWelcomeDoneAndCheckChange() {
        Assert.assertEquals(false, preferenceHelper.welcomeDone)
        preferenceHelper.welcomeDone = true
        Assert.assertEquals(true, preferenceHelper.welcomeDone)
    }
}