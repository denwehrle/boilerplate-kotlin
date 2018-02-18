package com.denwehrle.boilerplate.factory

import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.factory.DataFactory.Companion.randomString

/**
 * @author Dennis Wehrle
 */
class ContactFactory {

    companion object {

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

        fun makeContact(firstName: String, lastName: String): Contact {
            return Contact(randomString(), firstName, lastName, randomString())
        }
    }
}