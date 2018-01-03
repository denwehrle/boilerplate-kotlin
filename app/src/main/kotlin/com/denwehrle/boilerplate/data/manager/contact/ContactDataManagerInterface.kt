package com.denwehrle.boilerplate.data.manager.contact

import com.denwehrle.boilerplate.data.local.model.Contact
import io.reactivex.Flowable

/**
 * @author Dennis Wehrle
 */
interface ContactDataManagerInterface {

    fun getContacts(): Flowable<List<Contact>>
}