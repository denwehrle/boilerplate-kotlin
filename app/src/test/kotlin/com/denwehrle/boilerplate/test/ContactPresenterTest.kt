package com.denwehrle.boilerplate.test

import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.data.manager.contact.ContactDataManager
import com.denwehrle.boilerplate.factory.ContactFactory
import com.denwehrle.boilerplate.ui.contact.ContactMvpView
import com.denwehrle.boilerplate.ui.contact.ContactPresenter
import com.denwehrle.boilerplate.util.helper.RxSchedulersOverrideRule
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Dennis Wehrle
 */
@RunWith(MockitoJUnitRunner::class)
class ContactPresenterTest {

    @Rule
    @JvmField
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    lateinit var presenter: ContactPresenter

    @Mock
    private lateinit var mockMvpView: ContactMvpView

    @Mock
    private lateinit var mockContactDataManager: ContactDataManager

    @Before
    fun setUp() {
        presenter = ContactPresenter(mockContactDataManager)
        presenter.attachView(mockMvpView)
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }

    @Test
    fun loadDataReturnsData() {
        val data = ContactFactory.makeContactList(10)
        whenever(mockContactDataManager.getContacts()).thenReturn(Flowable.just(data))

        presenter.loadData()
        verify<ContactMvpView>(mockMvpView).showData(data)
        verify<ContactMvpView>(mockMvpView, never()).showError()
    }

    /*@Test
    fun loadDataReturnsEmptyList() {
        whenever(mockContactDataManager.getContacts()).thenReturn(Flowable.just(emptyList()))

        presenter.loadData()
        verify<ContactMvpView>(mockMvpView, never()).showData(ArgumentMatchers.anyList<Contact>())
        verify<ContactMvpView>(mockMvpView, never()).showError()
    }*/

    @Test
    fun loadDataFails() {
        whenever(mockContactDataManager.getContacts()).thenReturn(Flowable.error<List<Contact>>(RuntimeException()))

        presenter.loadData()
        verify<ContactMvpView>(mockMvpView).showError()
        verify<ContactMvpView>(mockMvpView, never()).showData(ArgumentMatchers.anyList<Contact>())
    }
}