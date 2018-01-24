package com.denwehrle.boilerplate.ui.contact

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.nhaarman.mockito_kotlin.whenever
import com.denwehrle.boilerplate.R
import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.test.TestApp
import com.denwehrle.boilerplate.test.factory.ContactFactory
import com.denwehrle.boilerplate.test.util.RecyclerViewMatcher
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.matches

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

    @Test
    fun contactDisplay() {
        val contacts = ContactFactory.makeContactList(1)
        stubGetContacts(Flowable.just(contacts))
        activity.launchActivity(null)

        //checkDetailsDisplay(contacts[0], 0)
    }

    @Test
    fun contactsAreScrollable() {
        val contacts = ContactFactory.makeContactList(50)
        stubGetContacts(Flowable.just(contacts))
        activity.launchActivity(null)

        Thread.sleep(1500)

        contacts.forEachIndexed { index, contact ->
            onView(ViewMatchers.withId(R.id.recyclerView))
                    .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))
            //checkDetailsDisplay(contact, index)
        }
    }

    /********* Helper Methods *********/

    private fun withRecyclerView(recyclerViewId: Int) = RecyclerViewMatcher(recyclerViewId)

    private fun checkDetailsDisplay(contact: Contact, position: Int) {
        onView(withRecyclerView(R.id.recyclerView)
                .atPosition(position))
                .check(matches(hasDescendant(withText(contact.email))))
    }

    private fun stubGetContacts(contacts: Flowable<List<Contact>>) {
        whenever(TestApp.appComponent().contactDataManager().getContacts()).thenReturn(contacts)
    }
}