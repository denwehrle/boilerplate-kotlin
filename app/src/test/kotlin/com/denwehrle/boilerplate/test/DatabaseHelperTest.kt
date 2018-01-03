package com.denwehrle.boilerplate.test

import android.arch.persistence.room.Room
import com.denwehrle.boilerplate.data.local.helper.DatabaseHelper
import com.denwehrle.boilerplate.util.RxSchedulersOverrideRule
import com.denwehrle.boilerplate.util.factory.ContactFactory
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals

/**
 * @author Dennis Wehrle
 */
@RunWith(RobolectricTestRunner::class)
class DatabaseHelperTest {

    @Rule
    @JvmField
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var databaseHelper: DatabaseHelper

    @Before
    fun initDb() {
        databaseHelper = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application, DatabaseHelper::class.java).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() {
        databaseHelper.close()
    }

    @Test
    fun dbCreationSuccess() {
        assertEquals(true, true)
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertAndLoad() {
        val contact = ContactFactory.makeContact("foo", "bar")
        databaseHelper.contactDao().insertAll(contact)

        val loaded = databaseHelper.contactDao().findByFirstName(contact.firstName).blockingGet()
        assertEquals(loaded.firstName, ("foo"))

        val replacement = ContactFactory.makeContact("foo2", "")
        databaseHelper.contactDao().insertAll(replacement)

        val loadedReplacement = databaseHelper.contactDao().findByFirstName("foo2").blockingGet()
        assertEquals(loadedReplacement.firstName, ("foo2"))
    }
}
