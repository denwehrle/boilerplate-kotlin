package com.denwehrle.boilerplate.data.remote.model

/**
 * @author Dennis Wehrle
 */
class RemoteContact(var email: String, var name: Name, var picture: Picture) {

    class Name(var first: String, var last: String)

    class Picture(var medium: String)
}