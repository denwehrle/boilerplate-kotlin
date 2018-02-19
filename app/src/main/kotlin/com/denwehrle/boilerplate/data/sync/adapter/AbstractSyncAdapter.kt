package com.denwehrle.boilerplate.data.sync.adapter

import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Dennis Wehrle
 */
abstract class AbstractSyncAdapter(context: Context, autoInitialize: Boolean) : AbstractThreadedSyncAdapter(context, autoInitialize) {

    /**
     * All disposables will be stored inside the [CompositeDisposable] so we can clear
     * them all at the same time.
     */
    protected val disposables = CompositeDisposable()
}