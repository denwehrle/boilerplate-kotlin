package com.denwehrle.boilerplate.util.sync

import android.accounts.Account
import android.content.ContentResolver
import android.os.Bundle

/**
 * @author Dennis Wehrle
 */
object SyncUtils {

    private const val SYNC_FREQUENCY = (60 * 60 * 24 * 7).toLong()  // 7 days (in seconds)
    private const val CONTENT_AUTHORITY = "com.denwehrle.boilerplate"

    fun createSyncAccount(account: Account) {

        // Inform the system that this account supports sync
        ContentResolver.setIsSyncable(account, CONTENT_AUTHORITY, 1)

        // Inform the system that this account is eligible for auto sync when the network is up
        ContentResolver.setSyncAutomatically(account, CONTENT_AUTHORITY, true)

        // Recommend a schedule for automatic synchronization. The system may modify this based
        // on other scheduled syncs and network utilization.
        ContentResolver.addPeriodicSync(account, CONTENT_AUTHORITY, Bundle(), SYNC_FREQUENCY)
    }

    fun triggerRefresh(account: Account) {
        val bundle = Bundle()

        // Disable sync backoff and ignore sync preferences. In other words...perform sync NOW!
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true)
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true)
        ContentResolver.requestSync(account, CONTENT_AUTHORITY, bundle)
    }
}
