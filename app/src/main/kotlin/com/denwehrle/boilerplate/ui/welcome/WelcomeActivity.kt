package com.denwehrle.boilerplate.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.denwehrle.boilerplate.R
import com.denwehrle.boilerplate.ui.base.BaseActivity
import com.denwehrle.boilerplate.ui.login.LoginActivity
import com.denwehrle.boilerplate.ui.welcome.section.WelcomeSectionFragment
import kotlinx.android.synthetic.main.content_welcome.*
import javax.inject.Inject


/**
 * @author Dennis Wehrle
 */
class WelcomeActivity : BaseActivity(), WelcomeMvpView, ViewPager.OnPageChangeListener, View.OnClickListener {

    /**
     * To make classed injectable make sure they have a constructor
     * with the @Inject annotation.
     */
    @Inject
    lateinit var presenter: WelcomePresenter
    @Inject
    lateinit var adapter: WelcomeAdapter

    /**
     * For AndroidInjection.inject(this) to work the Activity/Fragment/Service has to be
     * registered in injection/module/BindingModule. Make sure it's called before
     * super.onCreate() to prevent unexpected crashed if the task gets suspended by the OS.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        presenter.attachView(this)
    }

    /**
     * We check the scrolling position to toggle between the next
     * and done button if we reach the last page.
     */
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (position == adapter.count - 1) {
            nextButton.visibility = View.GONE
            doneButton.visibility = View.VISIBLE
        } else {
            nextButton.visibility = View.VISIBLE
            doneButton.visibility = View.GONE
        }
    }

    /**
     * We respond to the click events by ether finish the
     * activity or show the next screen.
     */
    override fun onClick(view: View) {
        when (view.id) {
            skipButton.id, doneButton.id -> {
                presenter.setWelcomeDone()
                val intent = Intent(application, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left)
                finish()
            }
            nextButton.id -> viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }
    }

    override fun onPageSelected(position: Int) {}

    override fun onPageScrollStateChanged(state: Int) {}


    /********* Mvp Method Implementations *********/

    override fun setUpViewPager() {
        adapter.addFragment(WelcomeSectionFragment.newInstance(R.string.welcome_title_1, R.string.welcome_text_1, R.drawable.ic_android))
        adapter.addFragment(WelcomeSectionFragment.newInstance(R.string.welcome_title_2, R.string.welcome_text_2, R.drawable.ic_beach))
        adapter.addFragment(WelcomeSectionFragment.newInstance(R.string.welcome_title_3, R.string.welcome_text_3, R.drawable.ic_cake))

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)
        adapter.notifyDataSetChanged()
    }

    override fun setUpClickListener() {
        skipButton.setOnClickListener(this)
        nextButton.setOnClickListener(this)
        doneButton.setOnClickListener(this)
    }

    /**
     * Make sure to detach the presenter so we don't create a memory leak.
     */
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}