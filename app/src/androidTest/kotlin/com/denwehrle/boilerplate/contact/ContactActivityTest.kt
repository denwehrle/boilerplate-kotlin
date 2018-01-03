package com.denwehrle.boilerplate.contact

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.test.TestApp
import com.denwehrle.boilerplate.test.factory.ContactFactory
import com.denwehrle.boilerplate.ui.contact.ContactActivity
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @author Dennis Wehrle
 */
@RunWith(AndroidJUnit4::class)
class ContactActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<ContactActivity>(ContactActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubGetContacts(Flowable.just(ContactFactory.makeContactList(10)))
        activity.launchActivity(null)
    }

    private fun stubGetContacts(contacts: Flowable<List<Contact>>) {
        whenever(TestApp.appComponent().dataManager().getContacts()).thenReturn(contacts)
    }
}