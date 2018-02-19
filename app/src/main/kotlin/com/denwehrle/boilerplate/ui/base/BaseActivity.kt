package com.denwehrle.boilerplate.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.denwehrle.boilerplate.data.auth.AuthenticatorUtils
import com.denwehrle.boilerplate.injection.module.BindingModule
import com.denwehrle.boilerplate.ui.LauncherActivity
import com.denwehrle.boilerplate.ui.login.LoginActivity
import com.denwehrle.boilerplate.ui.welcome.WelcomeActivity
import dagger.android.AndroidInjection

/**
 * @author Dennis Wehrle
 */
abstract class BaseActivity : AppCompatActivity() {

    private var shouldCheckAccount = true

    /**
     * For AndroidInjection.inject(this) to work the [AppCompatActivity] has to be registered
     * in the [BindingModule]. Make sure it's called before super.onCreate() to
     * prevent unexpected crashed if the task gets suspended by the OS.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        if (intent.getBooleanExtra("testMode", false)) {
            shouldCheckAccount = false
        }
    }

    override fun onResume() {
        super.onResume()
        if (shouldCheckAccount) {
            checkAccount()
        }
    }

    private fun checkAccount() {
        if (this !is LoginActivity && this !is WelcomeActivity) {
            if (AuthenticatorUtils.getAccount(this) == null) {
                LauncherActivity.startAuthenticatorActivity(this, true)
                finish()
            }
        }
    }
}