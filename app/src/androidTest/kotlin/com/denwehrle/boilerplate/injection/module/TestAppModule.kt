package com.denwehrle.boilerplate.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.denwehrle.boilerplate.BuildConfig
import com.denwehrle.boilerplate.data.manager.contact.ContactDataManagerInterface
import com.denwehrle.boilerplate.data.local.helper.DatabaseHelper
import com.denwehrle.boilerplate.data.remote.endpoints.ContactService
import com.denwehrle.boilerplate.data.remote.factory.ContactServiceFactory
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Dennis Wehrle
 */
@Module
class TestAppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideDatabaseHelper(context: Context): DatabaseHelper {
        return Room.inMemoryDatabaseBuilder(context, DatabaseHelper::class.java).build()
    }

    @Provides
    @Singleton
    fun provideContactDataManager(): ContactDataManagerInterface {
        return mock()
    }

    @Provides
    @Singleton
    internal fun provideContactService(): ContactService {
        return ContactServiceFactory.makeContactService(BuildConfig.DEBUG)
    }
}