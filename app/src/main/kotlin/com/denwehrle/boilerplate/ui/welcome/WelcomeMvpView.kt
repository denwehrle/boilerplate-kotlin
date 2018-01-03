package com.denwehrle.boilerplate.ui.welcome

import com.denwehrle.boilerplate.ui.base.MvpView

/**
 * Interface as connection between the presenter and the view. These methods
 * basically describe the Use-Case which will be implemented.
 *
 * @author Dennis Wehrle
 */
interface WelcomeMvpView : MvpView {

    fun setUpViewPager()

    fun setUpClickListener()
}