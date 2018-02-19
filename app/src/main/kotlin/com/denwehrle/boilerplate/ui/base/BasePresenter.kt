package com.denwehrle.boilerplate.ui.base

/**
 * Base class that implements the [Presenter] interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().

 * @author Dennis Wehrle
 */
abstract class BasePresenter<T : MvpView> : Presenter<T> {

    private var view: T? = null

    val mvpView: T
        get() = view ?: throw MvpViewNotAttachedException()

    override fun attachView(mvpView: T) {
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

    class MvpViewNotAttachedException : RuntimeException("Please call presenter.attachView(mvpView) before requesting data of the presenter!")
}