package com.denwehrle.boilerplate.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.denwehrle.boilerplate.data.auth.AuthenticatorUtils
import com.denwehrle.boilerplate.ui.LauncherActivity
import com.denwehrle.boilerplate.ui.login.LoginActivity
import com.denwehrle.boilerplate.ui.welcome.WelcomeActivity
import dagger.android.AndroidInjection

/**
 * @author Dennis Wehrle
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * For AndroidInjection.inject(this) to work the Activity/Fragment/Service has to be
     * registered in injection/module/BindingModule. Make sure it's called before
     * super.onCreate() to prevent unexpected crashed if the task gets suspended by the OS.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        checkAccount()
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