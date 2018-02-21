package com.denwehrle.boilerplate.test.login

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.denwehrle.boilerplate.ui.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @author Dennis Wehrle
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<LoginActivity>(LoginActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        activity.launchActivity(null)
    }
}