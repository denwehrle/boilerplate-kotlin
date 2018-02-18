package com.denwehrle.boilerplate.data.sync.service

import android.content.AbstractThreadedSyncAdapter
import com.denwehrle.boilerplate.data.manager.contact.ContactDataManager
import com.denwehrle.boilerplate.data.sync.adapter.ContactDataSyncAdapter
import javax.inject.Inject

/**
 * @author @author Dennis Wehrle
 */
class ContactSyncService : AbstractSyncService() {

    /**
     * To make classed injectable make sure they have a constructor
     * with the @Inject annotation.
     */
    @Inject
    lateinit var contactDataManager: ContactDataManager

    /**
     * We provide the injected dependencies through the constructor because there is
     * no way to inject directly into the SyncAdapter.
     */
    override fun createSyncAdapter(): AbstractThreadedSyncAdapter {
        return ContactDataSyncAdapter(applicationContext, true, contactDataManager)
    }
}