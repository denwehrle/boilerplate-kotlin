package com.denwehrle.boilerplate.data.auth

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context

/**
 * @author Dennis Wehrle
 */
object AuthenticatorUtils {

    fun getAccount(context: Context): Account? {
        var account: Account? = null

        val accountManager = AccountManager.get(context)

        val acc = accountManager.getAccountsByType("com.denwehrle.boilerplate")
        if (acc.isNotEmpty()) {
            account = acc[0]
        }

        return account
    }

    @Suppress("DEPRECATION")
    fun removeAccount(context: Context) {
        val accountManager = AccountManager.get(context)

        if (getAccount(context) != null) {
            accountManager.removeAccount(getAccount(context), null, null)
        }
    }
}