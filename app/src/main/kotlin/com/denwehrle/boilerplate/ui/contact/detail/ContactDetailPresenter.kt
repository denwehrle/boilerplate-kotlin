package com.denwehrle.boilerplate.ui.contact.detail

import com.denwehrle.boilerplate.data.manager.contact.ContactDataManager
import com.denwehrle.boilerplate.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * The Presenter with its [ContactDataManager] are used for all the interaction with the data layer.
 * We use reactive streams as connection, so we can observe the database and get notified
 * by changes automatically.
 *
 * @author Dennis Wehrle
 */
class ContactDetailPresenter @Inject constructor(private val contactDataManager: ContactDataManager) : BasePresenter<ContactDetailMvpView>() {

    /**
     * All disposables will be stored inside the [CompositeDisposable] so we can clear
     * them all at the same time.
     */
    private val disposables = CompositeDisposable()

    /**
     * If we attach the Presenter there are tasks we can start regardless
     * the specific data, so let's do this here.
     */
    override fun attachView(mvpView: ContactDetailMvpView) {
        super.attachView(mvpView)

        this.mvpView.setUpToolbar()
    }

    /**
     * Make sure to clear the disposables so we don't create a memory leak.
     */
    override fun detachView() {
        super.detachView()
        disposables.clear()
    }

    /**
     * We subscribe to updates from the [ContactDataManager] and call the
     * associated methods inside our view.
     */
    fun loadData(email: String) {
        disposables.add(contactDataManager.getContactByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            mvpView.showData(it)
                        },
                        onError = {
                            Timber.e(it)
                            mvpView.showError()
                        }
                )
        )
    }
}