package com.denwehrle.boilerplate.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.denwehrle.boilerplate.R
import dagger.android.AndroidInjection

/**
 * @author Dennis Wehrle
 */
abstract class BaseDetailActivity : AppCompatActivity() {

    /**
     * For AndroidInjection.inject(this) to work the Activity/Fragment/Service has to be
     * registered in injection/module/BindingModule. Make sure it's called before
     * super.onCreate() to prevent unexpected crashed if the task gets suspended by the OS.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish() // Respond to the action bar's Up/Home button
        }
        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        super.finish()

        // finish activity with fancy slide out animation
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}