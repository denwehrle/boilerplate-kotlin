package com.denwehrle.boilerplate

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.BroadcastReceiver
import android.support.test.InstrumentationRegistry
import android.support.v4.app.Fragment
import com.denwehrle.boilerplate.injection.component.DaggerTestAppComponent
import com.denwehrle.boilerplate.injection.component.TestAppComponent
import dagger.android.*
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author Dennis Wehrle
 */
class TestApp : Application(), HasActivityInjector, HasServiceInjector, HasBroadcastReceiverInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var receiverInjector: DispatchingAndroidInjector<BroadcastReceiver>
    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    lateinit var appComponent: TestAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestAppComponent
                .builder()
                .application(this)
                .build()

        appComponent.inject(this)
    }

    companion object {
        fun appComponent(): TestAppComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext as TestApp).appComponent
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceInjector
    }

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> {
        return receiverInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}