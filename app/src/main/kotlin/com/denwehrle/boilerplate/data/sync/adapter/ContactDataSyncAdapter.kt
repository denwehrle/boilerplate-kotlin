package com.denwehrle.boilerplate.data.sync.adapter

import android.accounts.Account
import android.content.ContentProviderClient
import android.content.Context
import android.content.SyncResult
import android.os.Bundle
import com.denwehrle.boilerplate.data.manager.contact.ContactDataManager
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * @author Dennis Wehrle
 */
class ContactDataSyncAdapter(context: Context, autoInitialize: Boolean, private val contactDataManager: ContactDataManager) : AbstractSyncAdapter(context, autoInitialize) {

    override fun onPerformSync(account: Account, extras: Bundle, authority: String, provider: ContentProviderClient, syncResult: SyncResult) {
        syncContacts()
    }

    private fun syncContacts() {
        disposables.add(contactDataManager.syncContacts()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onError = {
                            Timber.e(it)
                        }
                )
        )
    }
}