package com.denwehrle.boilerplate.data.sync.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * We create a content provider, but we only use it for the sync adapter. None of these methods
 * actually get called so we just stub them.
 *
 * @author Dennis Wehrle
 */
abstract class AbstractProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        return true
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun query(uri: Uri, projection: Array<String>, selection: String, selectionArgs: Array<String>, sortOrder: String): Cursor? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String, selectionArgs: Array<String>): Int {
        return 0
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String, selectionArgs: Array<String>): Int {
        return 0
    }
}