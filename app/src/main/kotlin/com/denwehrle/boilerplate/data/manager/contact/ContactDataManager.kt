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
 * The [ContactDataManager] is the single point of communication between the different layers.
 *
 * @author Dennis Wehrle
 */
@Singleton
class ContactDataManager @Inject constructor(preferenceHelper: PreferenceHelper,
                                             private val databaseHelper: DatabaseHelper,
                                             private val contactService: ContactService,
                                             private val contactMapper: ContactMapper

) : BaseDataManager(preferenceHelper) {


    /********* Database / Service *********/

    /**
     * We request the data from the database and return it as observable stream. A [Flowable] can have
     * the state onNext(), onComplete() and onError() which we should react to in the calling method.
     */
    fun getContacts(): Flowable<List<Contact>> {
        return databaseHelper.contactDao().getAll()
    }

    /**
     * We request the data from the database and return it as observable stream. A [Single] can have
     * the state onComplete() or onError() which we should react to in the calling method.
     */
    fun getContactByEmail(email: String): Single<Contact> {
        return databaseHelper.contactDao().findByEmail(email)
    }

    /**
     * We request the data from the [ContactService] and return it as observable stream. A [Single] can have
     * the state onComplete() or onError() which we should react to in the calling method.
     *
     * After the sync we convert the remote object into a local one using the .map() function and our
     * [ContactMapper] and finally save the data into the database.
     */
    fun syncContacts(): Single<List<Contact>> {
        return contactService.getContacts()
                .map {
                    it.results.map {
                        contactMapper.mapFromRemote(it)
                    }
                }
                .flatMap {
                    saveContacts(it).toSingle { it }
                }
    }

    /**
     * Takes a list of objects to persist into the database. We return an [Completable]
     * so we can call it inside the .flatMap() operator and still return our initial data.
     */
    private fun saveContacts(contacts: List<Contact>): Completable {
        return Completable.defer {
            contacts.forEach {
                databaseHelper.contactDao().insertAll(it)
            }
            Completable.complete()
        }
    }
}