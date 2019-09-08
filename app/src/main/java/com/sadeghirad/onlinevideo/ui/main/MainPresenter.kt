package com.sadeghirad.onlinevideo.ui.main

import android.view.MenuItem
import com.sadeghirad.onlinevideo.R
import com.sadeghirad.onlinevideo.constants.AppConstants
import com.sadeghirad.onlinevideo.ui.base.IBaseView

class MainPresenter : MainMVP.Presenter {


    lateinit var mView: MainMVP.View

    private fun getMvpView(): MainMVP.View {
        return mView
    }

    override fun handleBottomNavigationClick(menuItemId: MenuItem): Boolean {

        AppConstants.isNavigatingBetweenFragments = true

        when (menuItemId.itemId) {
            R.id.bottom_navigation_video -> {
                getMvpView().showVideoFragment()
                return true
            }
            R.id.bottom_navigation_gif -> {
                getMvpView().showGifFragment()
                return true
            }
            R.id.bottom_navigation_me -> {
                getMvpView().showMeFragment()

                return true
            }
        }
        return false
    }

    override fun setView(view: IBaseView) {
        mView = view as MainMVP.View

        mView.setupViews()
    }


}