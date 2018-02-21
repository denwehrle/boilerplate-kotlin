package com.denwehrle.boilerplate.ui.login

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.denwehrle.boilerplate.R
import com.denwehrle.boilerplate.data.auth.AuthenticatorUtils
import com.denwehrle.boilerplate.ui.base.BaseActivity
import com.denwehrle.boilerplate.ui.contact.ContactActivity
import com.denwehrle.boilerplate.ui.welcome.WelcomeActivity
import com.denwehrle.boilerplate.util.sync.SyncUtils
import kotlinx.android.synthetic.main.content_login.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Dennis Wehrle
 */
class LoginActivity : BaseActivity(), LoginMvpView, View.OnClickListener {

    /**
     * To make classed injectable make sure they have a constructor
     * with the @Inject annotation.
     */
    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.attachView(this)

        if (!presenter.isWelcomeDone()) {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.signInButton ->
                Handler().postDelayed({
                    presenter.signIn("denwehrle", "12345678")
                }, 100)
        }
    }


    /********* Mvp Method Implementations *********/

    override fun setUpClickListener() {
        signInButton.setOnClickListener(this)
    }

    override fun showProgress(value: Boolean) {
        Timber.v("showProgress not implemented")
    }

    /**
     * If the data can not be loaded we react to the error accordingly.
     */
    override fun showError() {
        Timber.e("Something went wrong. Data could not be loaded.")
    }

    override fun showSignInSuccessful(username: String, password: String) {

        // only one user allowed, remove old account if new one is created
        if (AuthenticatorUtils.getAccount(applicationContext) != null) {
            AuthenticatorUtils.removeAccount(applicationContext)
        }

        val account = Account(username, "com.denwehrle.boilerplate")
        AccountManager.get(this).addAccountExplicitly(account, password, null)

        SyncUtils.createSyncAccount(account)

        Toast.makeText(this, "YEAH", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({

            // Actions to do after 3 seconds, time to download data in background
            showProgress(false)
            startActivity(Intent(this, ContactActivity::class.java))
            overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left)
            finish()
        }, 3000)
    }

    /**
     * Make sure to detach the presenter so we don't create a memory leak.
     */
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}