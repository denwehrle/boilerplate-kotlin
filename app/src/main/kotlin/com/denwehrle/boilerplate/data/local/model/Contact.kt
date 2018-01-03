package com.denwehrle.boilerplate.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Dennis Wehrle
 */
@Entity
data class Contact(

        @PrimaryKey
        var email: String,

        var firstName: String,
        var lastName: String,
        var avatar: String
)