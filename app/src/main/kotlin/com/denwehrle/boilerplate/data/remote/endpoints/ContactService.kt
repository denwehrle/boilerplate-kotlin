package com.denwehrle.boilerplate.data.remote.endpoints

import com.denwehrle.boilerplate.data.remote.model.RemoteContact
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Contact API
 *
 * @author Dennis Wehrle
 */
interface ContactService {

    @GET("?results=20&inc=name,picture,email&noinfo&seed=denwehrle")
    fun getContacts(): Flowable<ContactResponseList>

    class ContactResponseList(var results: List<RemoteContact>)
}