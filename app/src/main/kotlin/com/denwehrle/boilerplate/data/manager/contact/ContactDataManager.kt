package com.denwehrle.boilerplate.data.manager.contact

import com.denwehrle.boilerplate.data.local.helper.DatabaseHelper
import com.denwehrle.boilerplate.data.local.helper.PreferenceHelper
import com.denwehrle.boilerplate.data.local.model.Contact
import com.denwehrle.boilerplate.data.manager.base.BaseDataManager
import com.denwehrle.boilerplate.data.mapper.ContactMapper
import com.denwehrle.boilerplate.data.remote.endpoints.ContactService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Dennis Wehrle
 */
@Singleton
class ContactDataManager @Inject constructor(preferenceHelper: PreferenceHelper,
                                             private val databaseHelper: DatabaseHelper,
                                             private val contactService: ContactService,
                                             private val contactMapper: ContactMapper

) : BaseDataManager(preferenceHelper, databaseHelper), ContactDataManagerInterface {


    /********* Contact *********/

    override fun getContacts(): Flowable<List<Contact>> {
        return databaseHelper.contactDao().getAll()
    }

    fun getContactByEmail(email: String): Single<Contact> {
        return databaseHelper.contactDao().findByEmail(email)
    }

    fun syncContacts(): Flowable<List<Contact>> {
        return contactService.getContacts()
                .map {
                    it.results.map {
                        contactMapper.mapFromRemote(it)
                    }
                }
                .flatMap {
                    saveContacts(it).toSingle { it }.toFlowable()
                }
    }

    private fun saveContacts(contacts: List<Contact>): Completable {
        return Completable.defer {
            contacts.forEach {
                databaseHelper.contactDao().insertAll(it)
            }
            Completable.complete()
        }
    }
}