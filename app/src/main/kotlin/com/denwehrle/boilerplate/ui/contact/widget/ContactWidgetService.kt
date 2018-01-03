package com.denwehrle.boilerplate.ui.contact.widget

import android.content.Intent
import android.widget.RemoteViewsService
import com.denwehrle.boilerplate.data.manager.contact.ContactDataManager
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * @author Dennis Wehrle
 */
class ContactWidgetService : RemoteViewsService() {

    @Inject lateinit var contactDataManager: ContactDataManager

    /**
     * We provide the injected dependencies through the constructor because there is
     * no way to inject directly into the WidgetViewsFactory.
     */
    override fun onGetViewFactory(intent: Intent): RemoteViewsService.RemoteViewsFactory {
        return ContactWidgetAdapter(applicationContext, intent, contactDataManager)
    }

    /**
     * For AndroidInjection.inject(this) to work the Activity/Fragment/Service has to be
     * registered in injection/module/BindingModule. Make sure it's called before
     * super.onCreate() to prevent unexpected crashed if the task gets suspended by the OS.
     */
    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }
}