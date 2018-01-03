package com.denwehrle.boilerplate.ui.contact.detail

import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.ui.base.MvpView

/**
 * @author Dennis Wehrle
 */
interface ContactDetailMvpView : MvpView {

    fun setUpToolbar()

    fun showProgress(value: Boolean)

    fun showData(contact: Contact)

    fun showError()
}