package com.sadeghirad.onlinevideo.ui.main.navigationpages.me

import com.sadeghirad.onlinevideo.ui.base.IBaseView

class MePresenter : MeMVP.Presenter {
    lateinit var mView: MeMVP.View

    override fun setView(view: IBaseView) {
        mView = view as MeMVP.View
    }

    fun getView(): MeMVP.View {
        return mView
    }
}