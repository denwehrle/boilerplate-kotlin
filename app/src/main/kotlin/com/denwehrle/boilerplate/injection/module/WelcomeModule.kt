package com.denwehrle.boilerplate.injection.module

import com.denwehrle.boilerplate.injection.scope.PerActivity
import com.denwehrle.boilerplate.ui.welcome.WelcomeActivity
import com.denwehrle.boilerplate.ui.welcome.WelcomeAdapter
import dagger.Module
import dagger.Provides

/**
 * @author Dennis Wehrle
 */
@Module
class WelcomeModule {

    @Provides
    @PerActivity
    fun provideWelcomeViewPagerAdapter(activity: WelcomeActivity): WelcomeAdapter {
        return WelcomeAdapter(activity.supportFragmentManager)
    }
}