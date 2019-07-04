package com.sadeghirad.onlinevideo.ui.video

import android.annotation.SuppressLint
import android.util.Log
import com.sadeghirad.onlinevideo.http.apimodel.Video
import com.sadeghirad.onlinevideo.ui.base.IBaseView
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class VideoPresenter(val model: VideoMVP.Model) : VideoMVP.Presenter {
    lateinit var mView: VideoMVP.View

    @SuppressLint("CheckResult")
    override fun setView(view: IBaseView) {
        mView = view as VideoMVP.View

        model.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MaybeObserver<Video> {
                override fun onSuccess(t: Video) {
                    getView().loadData(t)

                }

                override fun onSubscribe(d: Disposable) {
                    Log.w("VideoPresenter", "onSubscribe")
                }

                override fun onError(e: Throwable) {
                    getView().showNetworkError()
                }

                override fun onComplete() {
                    Log.w("VideoPresenter", "onComplete")
                }
            })


    }


    fun getView(): VideoMVP.View {
        return mView
    }


}