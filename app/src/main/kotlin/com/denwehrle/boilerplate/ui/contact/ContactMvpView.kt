package com.denwehrle.boilerplate.ui.contact

import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.ui.base.MvpView

/**
 * @author Dennis Wehrle
 */
interface ContactMvpView : MvpView {

    fun setUpToolbar()

    fun setupRecyclerAdapter()

    fun setupSwipeRefresh()

    fun showData(contacts: List<Contact>)

    fun showError()
}