package com.sadeghirad.onlinevideo.ui.gif

import com.sadeghirad.onlinevideo.ui.base.IBaseView

class GifPresenter : GifMVP.Presenter {
    lateinit var mView: GifMVP.View

    override fun setView(view: IBaseView) {
        mView = view as GifMVP.View
    }

    fun getView():GifMVP.View{
        return mView
    }
}