package com.sadeghirad.onlinevideo.ui.main.navigationpages.video

import com.sadeghirad.onlinevideo.http.apimodel.Video
import com.sadeghirad.onlinevideo.ui.base.IBasePresenter
import com.sadeghirad.onlinevideo.ui.base.IBaseView
import io.reactivex.Maybe

interface VideoMVP {

    interface Model {
        fun getData(): Maybe<Video>
    }

    interface View : IBaseView {
        fun showNetworkError()

        fun showData(videos:Video)

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter : IBasePresenter {
        fun getPageData()
    }

}