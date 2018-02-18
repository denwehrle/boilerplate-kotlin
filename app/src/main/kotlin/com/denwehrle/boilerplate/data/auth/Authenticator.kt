package com.denwehrle.boilerplate.data.auth

import android.accounts.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import com.denwehrle.boilerplate.ui.login.LoginActivity
import timber.log.Timber

/**
 * @author Dennis Wehrle
 */
class Authenticator(private val context: Context) : AbstractAccountAuthenticator(context) {

    @Throws(NetworkErrorException::class)
    override fun addAccount(response: AccountAuthenticatorResponse, accountType: String, authTokenType: String, requiredFeatures: Array<String>, options: Bundle): Bundle {
        Timber.d("addAccount called")

        val intent = Intent(context, LoginActivity::class.java)
        intent.putExtra(SyncStateContract.Constants.ACCOUNT_TYPE, authTokenType)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)

        val result = Bundle()
        result.putParcelable(AccountManager.KEY_INTENT, intent)

        return result
    }

    @Throws(NetworkErrorException::class)
    override fun getAuthToken(response: AccountAuthenticatorResponse, account: Account, authTokenType: String, options: Bundle): Bundle? {
        return null
    }

    override fun getAuthTokenLabel(authTokenType: String): String? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun updateCredentials(response: AccountAuthenticatorResponse, account: Account, authTokenType: String, options: Bundle): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun hasFeatures(response: AccountAuthenticatorResponse, account: Account, features: Array<String>): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(response: AccountAuthenticatorResponse, account: Account, options: Bundle): Bundle? {
        return null
    }

    override fun editProperties(response: AccountAuthenticatorResponse, accountType: String): Bundle? {
        return null
    }
}