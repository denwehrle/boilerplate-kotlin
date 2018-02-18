package com.denwehrle.boilerplate.ui.welcome.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denwehrle.boilerplate.R
import com.denwehrle.boilerplate.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_welcome.view.*

/**
 * @author Dennis Wehrle
 */
class WelcomeSectionFragment : BaseFragment(), WelcomeSectionMvpView {

    /**
     * We use the companion object to statically create instances of the fragment.
     */
    companion object {
        fun newInstance(title: Int, description: Int, image: Int): WelcomeSectionFragment {
            val fragment = WelcomeSectionFragment()
            val bundle = Bundle(1)
            bundle.putInt("title", title)
            bundle.putInt("description", description)
            bundle.putInt("image", image)
            fragment.arguments = bundle
            return fragment

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_welcome, container, false)

        val bundle = arguments
        if (bundle != null) {
            val title = bundle.getInt("title")
            val description = bundle.getInt("description")
            val image = bundle.getInt("image")

            rootView.welcomeTitle.text = resources.getString(title)
            rootView.welcomeDescription.text = resources.getString(description)
            rootView.welcomeImage.setImageDrawable(resources.getDrawable(image))
        }
        return rootView
    }
}