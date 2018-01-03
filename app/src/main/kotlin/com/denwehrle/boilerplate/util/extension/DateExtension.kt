package com.denwehrle.boilerplate.util.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author Dennis Wehrle
 */

fun Date.simpleDateFormat(): String {
    return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)
}

fun Date.simpleTimeFormat(): String {
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(this)
}