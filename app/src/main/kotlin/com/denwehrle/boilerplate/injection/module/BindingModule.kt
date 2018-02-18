package com.denwehrle.boilerplate.injection.module

import com.denwehrle.boilerplate.data.sync.service.ContactSyncService
import com.denwehrle.boilerplate.injection.scope.PerActivity
import com.denwehrle.boilerplate.injection.scope.PerFragment
import com.denwehrle.boilerplate.ui.contact.ContactActivity
import com.denwehrle.boilerplate.ui.contact.detail.ContactDetailActivity
import com.denwehrle.boilerplate.ui.contact.widget.ContactWidgetProvider
import com.denwehrle.boilerplate.ui.contact.widget.ContactWidgetService
import com.denwehrle.boilerplate.ui.login.LoginActivity
import com.denwehrle.boilerplate.ui.welcome.WelcomeActivity
import com.denwehrle.boilerplate.ui.welcome.section.WelcomeSectionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Dennis Wehrle
 */
@Module
abstract class BindingModule {


    /********* Welcome *********/

    @PerActivity
    @ContributesAndroidInjector(modules = [WelcomeModule::class])
    abstract fun welcomeActivity(): WelcomeActivity

    @PerFragment
    @ContributesAndroidInjector
    abstract fun welcomeSectionFragment(): WelcomeSectionFragment


    /********* Contact *********/

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contactActivity(): ContactActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contactDetailActivity(): ContactDetailActivity


    /********* Misc *********/

    @PerActivity
    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity


    /********* Widget *********/

    @ContributesAndroidInjector
    abstract fun contactWidgetProvider(): ContactWidgetProvider

    @ContributesAndroidInjector
    abstract fun contactWidgetService(): ContactWidgetService


    /********* Service *********/

    @ContributesAndroidInjector
    abstract fun contactSyncService(): ContactSyncService
}