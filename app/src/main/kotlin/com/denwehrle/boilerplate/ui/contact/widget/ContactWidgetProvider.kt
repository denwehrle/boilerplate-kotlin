package com.denwehrle.boilerplate.ui.contact.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.RemoteViews
import com.denwehrle.boilerplate.BuildConfig
import com.denwehrle.boilerplate.R
import com.denwehrle.boilerplate.injection.module.BindingModule
import com.denwehrle.boilerplate.ui.contact.ContactActivity
import dagger.android.AndroidInjection
import timber.log.Timber
import java.util.*

/**
 * @author Dennis Wehrle
 */
class ContactWidgetProvider : AppWidgetProvider() {

    companion object {
        const val ACTION_WIDGET_REFRESH = BuildConfig.APPLICATION_ID + ".widgetRefresh"
        const val ACTION_WIDGET_ENABLED = "android.appwidget.action.APPWIDGET_ENABLED"
        const val ACTION_WIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE"

        fun getAllAppWidgetIds(context: Context, appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(context)): IntArray {
            return appWidgetManager.getAppWidgetIds(ComponentName(context, ContactWidgetProvider::class.java))
        }
    }

    /**
     * For AndroidInjection.inject(this) to work the [BroadcastReceiver] has to be registered
     * in the [BindingModule]. Make sure it's called before super.onReceive() to
     * prevent unexpected crashed if the task gets suspended by the OS.
     */
    override fun onReceive(context: Context, intent: Intent) {
        AndroidInjection.inject(this, context)
        super.onReceive(context, intent)

        val action = intent.action
        if (action.isEmpty()) return

        Timber.d("onReceive(), action: %s, widget ID: %d", action, intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1))

        when (action) {
            ACTION_WIDGET_REFRESH, ACTION_WIDGET_ENABLED, ACTION_WIDGET_UPDATE -> updateAllWidgetsUI(context)
            else -> Timber.d("Unknown action received, ignoring: %s", action)
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Timber.v("onUpdate: %s", Arrays.toString(appWidgetIds))

        updateAllWidgetsData(context)
    }

    private fun updateAllWidgetsData(context: Context) {

        // Show animated loading circle on all widgets
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = getAllAppWidgetIds(context, appWidgetManager)
        val views = RemoteViews(context.packageName, R.layout.widget_contact)
        views.setViewVisibility(R.id.progress, View.VISIBLE)
        appWidgetManager.updateAppWidget(appWidgetIds, views)
    }

    private fun updateAllWidgetsUI(context: Context) {
        Timber.d("updateAllWidgetsUI")
        val appWidgetManager = AppWidgetManager.getInstance(context)
        for (appWidgetId in getAllAppWidgetIds(context, appWidgetManager)) {
            updateWidgetUI(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateWidgetUI(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.widget_contact)

        views.setViewVisibility(R.id.progress, View.VISIBLE)

        // Create an Intent to launch ContactActivity when widget header is clicked
        val scheduleIntent = Intent(context, ContactActivity::class.java)
        val schedulePendingIntent = PendingIntent.getActivity(context, 0, scheduleIntent, 0)
        views.setOnClickPendingIntent(R.id.header, schedulePendingIntent)

        // Assign ScheduleWidgetService to widget as an RemoteAdapter
        val serviceIntent = Intent(context, ContactWidgetService::class.java)
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        serviceIntent.data = Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME))
        views.setRemoteAdapter(R.id.listView, serviceIntent)

        // Hide animated loading circle
        views.setViewVisibility(R.id.progress, View.GONE)

        // Tell the AppWidgetManager to perform an update on the current widget including its remote adapter
        appWidgetManager.updateAppWidget(appWidgetId, views)
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.listView)
    }
}
