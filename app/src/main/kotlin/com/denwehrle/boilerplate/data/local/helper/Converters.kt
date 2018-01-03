package com.denwehrle.boilerplate.data.local.helper

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * @author Dennis Wehrle
 */
class Converters {

    @TypeConverter
    fun longToDate(time: Long?) = if (time == null) Date() else Date(time)

    @TypeConverter
    fun dateToLong(date: Date?) = date?.time
}