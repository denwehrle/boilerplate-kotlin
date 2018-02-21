package com.denwehrle.boilerplate.test.welcome

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.denwehrle.boilerplate.ui.welcome.WelcomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @author Dennis Wehrle
 */
@RunWith(AndroidJUnit4::class)
class WelcomeActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<WelcomeActivity>(WelcomeActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        activity.launchActivity(null)
    }
}