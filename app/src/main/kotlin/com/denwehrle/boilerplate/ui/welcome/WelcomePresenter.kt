package com.denwehrle.boilerplate.ui.welcome

import com.denwehrle.boilerplate.data.manager.contact.ContactDataManager
import com.denwehrle.boilerplate.ui.base.BasePresenter
import javax.inject.Inject

/**
 * The Presenter with its [ContactDataManager] are used for all the interaction with the data layer.
 * We use reactive streams as connection, so we can observe the database and get notified
 * by changes automatically.
 *
 * @author Dennis Wehrle
 */
class WelcomePresenter @Inject constructor(private val contactDataManager: ContactDataManager) : BasePresenter<WelcomeMvpView>() {

    /**
     * If we attach the Presenter there are tasks we can start regardless
     * the specific data, so let's do this here.
     */
    override fun attachView(mvpView: WelcomeMvpView) {
        super.attachView(mvpView)

        this.mvpView.setUpViewPager()
        this.mvpView.setUpClickListener()
    }

    fun setWelcomeDone(){
        contactDataManager.setWelcomeDone()
    }
}