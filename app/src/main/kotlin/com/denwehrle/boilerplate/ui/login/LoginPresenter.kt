package com.denwehrle.boilerplate.ui.login

import com.denwehrle.boilerplate.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author Dennis Wehrle
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginMvpView>() {

    /**
     * All disposables will be stored inside the [CompositeDisposable] so we can clear
     * them all at the same time.
     */
    private val disposables: CompositeDisposable = CompositeDisposable()

    /**
     * If we attach the Presenter there are tasks we can start regardless
     * the specific data, so let's do this here.
     */
    override fun attachView(mvpView: LoginMvpView) {
        super.attachView(mvpView)

        this.mvpView.setUpClickListener()
    }

    /**
     * Make sure to clear the disposables so we don't create a memory leak.
     */
    override fun detachView() {
        super.detachView()
        disposables.clear()
    }

    fun signIn(username: String, password: String) {
        mvpView.showSignInSuccessful(username, password)
    }
}