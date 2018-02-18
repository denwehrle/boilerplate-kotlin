package com.denwehrle.boilerplate.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    /**
     * For AndroidInjection.inject(this) to work the Activity/Fragment/Service has to be
     * registered in injection/module/BindingModule. Make sure it's called before
     * super.onCreate() to prevent unexpected crashed if the task gets suspended by the OS.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}