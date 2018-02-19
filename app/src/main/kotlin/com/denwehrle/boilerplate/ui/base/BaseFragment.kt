package com.denwehrle.boilerplate.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.denwehrle.boilerplate.injection.module.BindingModule
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    /**
     * For AndroidInjection.inject(this) to work the [Fragment] has to be registered
     * in the [BindingModule]. Make sure it's called before super.onCreate() to
     * prevent unexpected crashed if the task gets suspended by the OS.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}