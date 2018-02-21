package com.denwehrle.boilerplate.ui.base

import io.reactivex.disposables.CompositeDisposable

/**
 * Base class that implements the [Presenter] interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().

 * @author Dennis Wehrle
 */
abstract class BasePresenter<T : MvpView> : Presenter<T> {

    /**
     * All disposables will be stored inside the [CompositeDisposable] so we can clear
     * them all at the same time.
     */
    protected val disposables: CompositeDisposable = CompositeDisposable()

    private var view: T? = null

    val mvpView: T
        get() = view ?: throw MvpViewNotAttachedException()

    override fun attachView(mvpView: T) {
        view = mvpView
    }

    /**
     * Make sure to clear the disposables so we don't create a memory leak.
     */
    override fun detachView() {
        view = null
        disposables.clear()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call presenter.attachView(mvpView) before requesting data of the presenter!")
}