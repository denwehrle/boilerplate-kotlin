package com.denwehrle.boilerplate.test

import com.denwehrle.boilerplate.data.local.helper.DatabaseHelper
import com.denwehrle.boilerplate.data.local.helper.PreferenceHelper
import com.denwehrle.boilerplate.data.manager.contact.ContactDataManager
import com.denwehrle.boilerplate.data.mapper.ContactMapper
import com.denwehrle.boilerplate.data.remote.endpoints.ContactService
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Dennis Wehrle
 */
@RunWith(MockitoJUnitRunner::class)
class ContactDataManagerTest {

    private lateinit var contactDataManager: ContactDataManager

    @Mock lateinit var preferenceHelper: PreferenceHelper
    @Mock lateinit var contactService: ContactService
    @Mock lateinit var contactMapper: ContactMapper
    @Mock lateinit var databaseHelper: DatabaseHelper

    @Before
    fun setUp() {
        contactDataManager = ContactDataManager(preferenceHelper, databaseHelper, contactService, contactMapper)
    }

    @Test
    fun setWelcomeDoneAndCheck() {
        assertEquals(false, contactDataManager.isWelcomeDone())

        // set value but preferenceHelper so return expected value
        contactDataManager.setWelcomeDone()
        whenever(contactDataManager.isWelcomeDone()).thenReturn(true)

        assertEquals(true, contactDataManager.isWelcomeDone())
    }
}