package com.denwehrle.boilerplate.data.manager.contact

import com.denwehrle.boilerplate.data.local.model.Contact
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @author Dennis Wehrle
 */
interface ContactDataManagerInterface {

    fun getContacts(): Flowable<List<Contact>>

    fun getContactByEmail(email: String): Single<Contact>
}