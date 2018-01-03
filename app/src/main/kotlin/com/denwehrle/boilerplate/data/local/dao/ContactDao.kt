package com.denwehrle.boilerplate.data.local.dao

import android.arch.persistence.room.*
import com.denwehrle.boilerplate.data.local.model.Contact
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @author Dennis Wehrle
 */
@Dao
interface ContactDao {

    @Query("SELECT * FROM contact " +
            "ORDER BY firstName")
    fun getAll(): Flowable<List<Contact>>

    @Query("SELECT * FROM contact " +
            "WHERE firstName = :firstName")
    fun findByFirstName(firstName: String): Single<Contact>

    @Query("SELECT * FROM contact " +
            "WHERE email = :email")
    fun findByEmail(email: String): Single<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg data: Contact): List<Long>

    @Update
    fun updateUsers(vararg data: Contact): Int

    @Query("DELETE FROM contact")
    fun clearTable()
}