package com.denwehrle.boilerplate.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.denwehrle.boilerplate.data.auth.AuthenticatorUtils
import com.denwehrle.boilerplate.ui.contact.ContactActivity
import com.denwehrle.boilerplate.ui.login.LoginActivity

/**
 * @author Dennis Wehrle
 */
class LauncherActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (AuthenticatorUtils.getAccount(this) != null) {
            startActivity(Intent(this, ContactActivity::class.java))
        } else {
            startAuthenticatorActivity(this, false)
        }
        finish()
    }

    override fun onPostResume() {
        super.onPostResume()
        finish()
    }

    companion object {

        fun startAuthenticatorActivity(context: Context, startAsNewTask: Boolean) {
            AuthenticatorUtils.removeAccount(context)

            val intent = Intent(context, LoginActivity::class.java)

            if (startAsNewTask) {
                setFlagsNewTask(intent)
            }

            context.startActivity(intent)
        }

        private fun setFlagsNewTask(intent: Intent) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
    }
}