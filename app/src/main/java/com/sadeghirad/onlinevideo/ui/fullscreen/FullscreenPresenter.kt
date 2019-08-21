package com.sadeghirad.onlinevideo.ui.fullscreen

import com.sadeghirad.onlinevideo.constants.AppConstants
import com.sadeghirad.onlinevideo.ui.base.IBaseView

class FullscreenPresenter : FullscreenMVP.Presenter {


    lateinit var mView: FullscreenMVP.View

    private fun getMvpView(): FullscreenMVP.View {
        return mView
    }

    override fun setNavigatiingToFullscreenFalse() {
        AppConstants.isNavigatingToFullScreen = false
    }

    override fun handleFullscreenButtonClick() {
        getMvpView().closeFullscreenActivity()
    }

    override fun setView(view: IBaseView) {
        mView = view as FullscreenMVP.View
    }


}