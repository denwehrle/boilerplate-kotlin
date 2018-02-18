package com.denwehrle.boilerplate.ui.contact

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.denwehrle.boilerplate.R
import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.ui.base.BaseActivity
import com.denwehrle.boilerplate.ui.contact.detail.ContactDetailActivity
import kotlinx.android.synthetic.main.activity_contact.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Dennis Wehrle
 */
class ContactActivity : BaseActivity(), ContactMvpView {

    /**
     * To make classed injectable make sure they have a constructor
     * with the @Inject annotation.
     */
    @Inject
    lateinit var presenter: ContactPresenter
    @Inject
    lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        presenter.attachView(this)
        presenter.loadData()
    }


    /********* Mvp Method Implementations *********/

    override fun setUpToolbar() {
        setSupportActionBar(toolbar)
    }

    /**
     * Basic setup of the RecyclerView. We add an ItemDecoration for vertical divider
     * lines and ItemClickSupport to react to item clicks.
     */
    override fun setupRecyclerAdapter() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        adapter.setOnItemClickListener(
                object : ContactAdapter.OnItemClickListener {
                    override fun onItemClick(contact: Contact) {
                        startActivity(Intent(applicationContext, ContactDetailActivity::class.java).putExtra("email", contact.email))
                        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left)
                    }
                }
        )
    }

    override fun showProgress(value: Boolean) {
        Timber.v("showProgress not implemented")
    }

    /**
     * We take the loaded data, set it to the corresponding adapter and notify the
     * UI that something has changed.
     */
    override fun showData(contacts: List<Contact>) {
        adapter.contacts = contacts
        adapter.notifyDataSetChanged()
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