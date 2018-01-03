package com.denwehrle.boilerplate.ui.contact.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.denwehrle.boilerplate.R
import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.data.manager.contact.ContactDataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * @author Dennis Wehrle
 */
class ContactWidgetAdapter(private val context: Context, intent: Intent, private val contactDataManager: ContactDataManager) : RemoteViewsService.RemoteViewsFactory {

    /**
     * All disposables will be stored inside the [CompositeDisposable] so we can clear
     * them all at the same time.
     */
    private val disposables = CompositeDisposable()

    private val appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
    private var contacts = emptyList<Contact>()

    override fun onCreate() {
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onDataSetChanged() {
        if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
            val token = Binder.clearCallingIdentity()
            try {
                disposables.add(contactDataManager.getContacts()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                                onNext = {
                                    contacts = it
                                },
                                onError = {
                                    Timber.e(it)
                                }
                        )
                )
            } finally {
                Binder.restoreCallingIdentity(token)
            }
        }
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getViewAt(position: Int): RemoteViews {
        val row = RemoteViews(context.packageName, R.layout.item_widget_contact)
        if (contacts.isEmpty()) {

        } else if (position < contacts.size) {
            val contact = contacts[position]
            row.setTextViewText(R.id.textFirstName, contact.firstName.capitalize())
            row.setTextViewText(R.id.textLastName, contact.lastName.capitalize())
            row.setTextViewText(R.id.textEmail, contact.email)
        }
        return row
    }

    override fun getCount(): Int {
        return contacts.size
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    /**
     * Make sure to detach the presenter so we don't create a memory leak.
     */
    override fun onDestroy() {
        disposables.clear()
    }
}