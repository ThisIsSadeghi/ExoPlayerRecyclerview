package com.sadeghirad.onlinevideo.ui.fullscreen

import com.sadeghirad.onlinevideo.ui.base.IBasePresenter
import com.sadeghirad.onlinevideo.ui.base.IBaseView

interface FullscreenMVP {

    interface Model

    interface View : IBaseView {
        fun closeFullscreenActivity()

        fun makeActivityFullscreen()

        fun delayedHide()

        fun hide()

        fun changeFullscreenButtonToExit()

        fun setupPlayer()
    }

    interface Presenter: IBasePresenter {
        fun handleFullscreenButtonClick()
        fun setNavigatiingToFullscreenFalse()


    }

}