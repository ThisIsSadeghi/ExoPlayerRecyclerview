package com.sadeghirad.onlinevideo.ui.main.navigationpages.video

import android.annotation.SuppressLint
import com.sadeghirad.onlinevideo.http.apimodel.Video
import com.sadeghirad.onlinevideo.ui.base.IBaseView
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class VideoPresenter(private val model: VideoMVP.Model) :
    VideoMVP.Presenter {


    lateinit var mView: VideoMVP.View

    @SuppressLint("CheckResult")
    override fun setView(view: IBaseView) {
        mView = view as VideoMVP.View
    }


    fun getView(): VideoMVP.View {
        return mView
    }

    override fun getPageData() {
        getView().showProgressBar()


        model.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MaybeObserver<Video> {
                override fun onSuccess(video: Video) {

                    getView().showData(video)
                    getView().hideProgressBar()

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    getView().showNetworkError()
                    getView().hideProgressBar()
                }

                override fun onComplete() {
                }
            })
    }
}