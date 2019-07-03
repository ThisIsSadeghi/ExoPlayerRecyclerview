package com.sadeghirad.onlinevideo.ui.main

import android.view.MenuItem
import com.sadeghirad.onlinevideo.ui.base.IBasePresenter
import com.sadeghirad.onlinevideo.ui.base.IBaseView

interface MainMVP {

    interface Model {

    }

    interface View : IBaseView {
        fun setupViews()

        fun showVideoFragment()

        fun showGifFragment()

        fun showMeFragment()

    }

    interface Presenter : IBasePresenter {
        fun handleBottomNavigationClick(menuItemId: MenuItem): Boolean

    }

}