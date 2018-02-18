package com.denwehrle.boilerplate.data.auth

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * @author Dennis Wehrle
 */
class AuthenticatorService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return Authenticator(this).iBinder
    }
}