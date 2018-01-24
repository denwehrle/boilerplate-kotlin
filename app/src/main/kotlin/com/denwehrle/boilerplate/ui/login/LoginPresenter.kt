package com.denwehrle.boilerplate.ui.login

import com.denwehrle.boilerplate.data.manager.login.LoginDataManager
import com.denwehrle.boilerplate.data.remote.model.LoginResult
import com.denwehrle.boilerplate.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * The Presenter with its [LoginDataManager] are used for all the interaction with the data layer.
 * We use reactive streams as connection, so we can observe the database and get notified
 * by changes automatically.
 *
 * @author Dennis Wehrle
 */
class LoginPresenter @Inject constructor(private val dataManager: LoginDataManager) : BasePresenter<LoginMvpView>() {

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
        disposables.add(dataManager.signIn(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            when (it) {
                                LoginResult.SUCCESS -> mvpView.showSignInSuccessful(username, password)
                                else -> {
                                    mvpView.showProgress(false)
                                    mvpView.showError()
                                }
                            }
                        },
                        onError = {
                            mvpView.showProgress(false)
                            mvpView.showError()
                        }
                )
        )
    }

    fun isWelcomeDone(): Boolean {
        return dataManager.isWelcomeDone()
    }
}