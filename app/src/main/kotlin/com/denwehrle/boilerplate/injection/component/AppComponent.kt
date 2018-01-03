package com.denwehrle.boilerplate.injection.component

import android.app.Application
import com.denwehrle.boilerplate.App
import com.denwehrle.boilerplate.injection.module.AppModule
import com.denwehrle.boilerplate.injection.module.BindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Dennis Wehrle
 */
@Singleton
@Component(modules = [BindingModule::class, AppModule::class, AndroidSupportInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}