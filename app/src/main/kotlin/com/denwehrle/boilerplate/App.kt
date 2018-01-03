package com.denwehrle.boilerplate

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Build
import android.support.multidex.MultiDex
import android.support.v4.app.Fragment
import com.denwehrle.boilerplate.injection.component.DaggerAppComponent
import com.denwehrle.boilerplate.util.notification.NotificationUtils
import com.facebook.stetho.Stetho
import dagger.android.*
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Dennis Wehrle
 */
class App : Application(), HasActivityInjector, HasServiceInjector, HasBroadcastReceiverInjector, HasSupportFragmentInjector {

    /**
     * We register our injectors, so we can inject all type of objects
     * inside our classes later. These injectors use the provider methods
     * defined in the /injection folder.
     */
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var receiverInjector: DispatchingAndroidInjector<BroadcastReceiver>
    @Inject lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()

        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)*/

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

        setupDebugUtils()

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationUtils(applicationContext).createChannels()
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun setupDebugUtils() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
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