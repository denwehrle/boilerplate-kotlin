package com.denwehrle.boilerplate.data.remote.endpoints

import com.denwehrle.boilerplate.data.remote.model.RemoteContact
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Contact API
 *
 * @author Dennis Wehrle
 */
interface ContactService {

    @GET("?results=20&inc=name,picture,email&noinfo&seed=denwehrle")
    fun getContacts(): Single<ContactResponseList>

    class ContactResponseList(var results: List<RemoteContact>)
}