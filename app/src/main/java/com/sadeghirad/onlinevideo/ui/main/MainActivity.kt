package com.sadeghirad.onlinevideo.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sadeghirad.onlinevideo.R
import com.sadeghirad.onlinevideo.ui.base.BaseViewActivity
import com.sadeghirad.onlinevideo.ui.gif.GifFragment
import com.sadeghirad.onlinevideo.ui.me.MeFragment
import com.sadeghirad.onlinevideo.ui.video.VideoFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseViewActivity(), MainMVP.View {

    @Inject
    lateinit var presenter: MainMVP.Presenter

    private var videoFragment: VideoFragment? = null
    private var gifFragment: GifFragment? = null
    private var meFragment: MeFragment? = null
    private var currentFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getApplicationComponent().inject(this)
    }


    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun setupViews() {
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            presenter.handleBottomNavigationClick(menuItem)
        }

        bottomNavigation.selectedItemId = R.id.bottom_navigation_video

    }

    override fun showVideoFragment() {

        if (isDestroyed)
            return
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (videoFragment == null) {
            videoFragment = VideoFragment.getInstance()
            fragmentTransaction.add(R.id.containerFragment, videoFragment!!)
            if (currentFragment != null)
                fragmentTransaction.detach(currentFragment!!)
        } else {
            currentFragment?.let { fragmentTransaction.detach(it) }
            fragmentTransaction.attach(videoFragment!!)
        }

        fragmentTransaction.commit()
        currentFragment = videoFragment

    }

    override fun showGifFragment() {

        if (isDestroyed)
            return
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (gifFragment == null) {
            gifFragment = GifFragment.getInstance()
            fragmentTransaction.add(R.id.containerFragment, gifFragment!!)
            if (currentFragment != null)
                fragmentTransaction.detach(currentFragment!!)
        } else {
            currentFragment?.let { fragmentTransaction.detach(it) }
            fragmentTransaction.attach(gifFragment!!)
        }

        fragmentTransaction.commit()
        currentFragment = gifFragment

    }

    override fun showMeFragment() {

        if (isDestroyed)
            return
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (meFragment == null) {
            meFragment = MeFragment.getInstance()
            fragmentTransaction.add(R.id.containerFragment, meFragment!!)
            if (currentFragment != null)
                fragmentTransaction.detach(currentFragment!!)
        } else {
            currentFragment?.let { fragmentTransaction.detach(it) }
            fragmentTransaction.attach(meFragment!!)
        }

        fragmentTransaction.commit()
        currentFragment = meFragment
    }

}