package com.denwehrle.boilerplate.ui.contact.detail

import android.os.Bundle
import com.denwehrle.boilerplate.R
import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.ui.base.BaseDetailActivity
import kotlinx.android.synthetic.main.activity_contact_detail.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Dennis Wehrle
 */
class ContactDetailActivity : BaseDetailActivity(), ContactDetailMvpView {

    @Inject
    lateinit var presenter: ContactDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val email = intent.getStringExtra("email") ?: ""

        presenter.attachView(this)
        presenter.loadData(email)
    }


    /********* Mvp Method Implementations *********/

    override fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
    }

    override fun showProgress(value: Boolean) {
        Timber.v("showProgress not implemented")
    }

    /**
     * We take the loaded data, set it to the corresponding adapter and notify the
     * UI that something has changed.
     */
    override fun showData(contact: Contact) {
        title = contact.email
    }

    /**
     * If the data can not be loaded we react to the error accordingly.
     */
    override fun showError() {
        Timber.e("Something went wrong. Data could not be loaded.")
    }

    /**
     * Make sure to detach the presenter so we don't create a memory leak.
     */
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}