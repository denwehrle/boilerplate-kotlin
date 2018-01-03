package com.denwehrle.boilerplate.test.factory

import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.test.factory.DataFactory.Factory.randomUuid

/**
 * @author Dennis Wehrle
 */
class ContactFactory {

    companion object Factory {

        fun makeContactList(count: Int): List<Contact> {
            val contacts = mutableListOf<Contact>()
            repeat(count) {
                contacts.add(makeContact())
            }
            return contacts
        }

        private fun makeContact(): Contact {
            return Contact(randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }
    }
}