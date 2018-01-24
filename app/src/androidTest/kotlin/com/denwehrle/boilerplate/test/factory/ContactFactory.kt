package com.denwehrle.boilerplate.test.factory

import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.test.factory.DataFactory.Companion.randomString

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

        fun makeContact(): Contact {
            return Contact(email = randomString(),
                    firstName = randomString(),
                    lastName = randomString(),
                    avatar = randomString()
            )
        }
    }
}