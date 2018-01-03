package com.denwehrle.boilerplate.data.mapper

import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.data.remote.model.RemoteContact
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Dennis Wehrle
 */
@Singleton
class ContactMapper @Inject constructor() {

    /**
     * Map an instance of a [RemoteContact] to a [Contact] model
     */
    fun mapFromRemote(contact: RemoteContact): Contact {
        return Contact(email = contact.email,
                firstName = contact.name.first,
                lastName = contact.name.last,
                avatar = contact.picture.medium
        )
    }
}