package com.denwehrle.boilerplate.test.contact

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.denwehrle.boilerplate.TestApp
import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.factory.ContactFactory
import com.denwehrle.boilerplate.ui.contact.detail.ContactDetailActivity
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @author Dennis Wehrle
 */
@RunWith(AndroidJUnit4::class)
class ContactDetailActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<ContactDetailActivity>(ContactDetailActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubGetContact(Single.just(ContactFactory.makeContact()))
        activity.launchActivity(Intent().putExtra("email", "denwehrle@gmail.com"))
    }


    /********* Helper Methods *********/

    private fun stubGetContact(contact: Single<Contact>) {
        whenever(TestApp.appComponent().contactDataManager().getContactByEmail("denwehrle@gmail.com")).thenReturn(contact)
    }
}