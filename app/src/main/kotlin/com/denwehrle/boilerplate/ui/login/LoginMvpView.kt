package com.denwehrle.boilerplate.ui.login

import com.denwehrle.boilerplate.ui.base.MvpView

/**
 * @author Dennis Wehrle
 */
interface LoginMvpView : MvpView {

    fun setUpClickListener()

    fun showProgress(value: Boolean)

    fun showError()

    fun showSignInSuccessful(username: String, password: String)
}