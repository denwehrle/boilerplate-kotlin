package com.denwehrle.boilerplate.injection.component

import android.app.Application
import com.denwehrle.boilerplate.data.manager.contact.ContactDataManagerInterface
import com.denwehrle.boilerplate.injection.module.BindingModule
import com.denwehrle.boilerplate.injection.module.TestAppModule
import com.denwehrle.boilerplate.test.TestApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Dennis Wehrle
 */
@Singleton
@Component(modules = [TestAppModule::class, BindingModule::class, AndroidSupportInjectionModule::class])
interface TestAppComponent : AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(app: TestApp)

    fun contactDataManager(): ContactDataManagerInterface
}