package com.denwehrle.boilerplate.data.sync.service

import android.app.Service
import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.os.IBinder

import dagger.android.AndroidInjection
import timber.log.Timber
import com.denwehrle.boilerplate.injection.module.BindingModule

/**
 * @author Dennis Wehrle
 */
abstract class AbstractSyncService : Service() {

    private var syncAdapter: AbstractThreadedSyncAdapter? = null
    private val syncAdapterLock = Any()

    abstract fun createSyncAdapter(): AbstractThreadedSyncAdapter

    /**
     * For AndroidInjection.inject(this) to work the [Service] has to be registered
     * in the [BindingModule]. Make sure it's called before super.onCreate() to
     * prevent unexpected crashed if the task gets suspended by the OS.
     */
    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
        Timber.v("onCreate")
        synchronized(syncAdapterLock) {
            if (syncAdapter == null) {
                syncAdapter = createSyncAdapter()
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Timber.v("onBind")
        return syncAdapter!!.syncAdapterBinder
    }

    override fun onDestroy() {
        Timber.v("onDestroy")
        super.onDestroy()
    }
}