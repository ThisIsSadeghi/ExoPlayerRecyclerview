package com.sadeghirad.onlinevideo.ui.main.navigationpages.video

import android.annotation.SuppressLint
import android.util.Log
import com.sadeghirad.onlinevideo.http.apimodel.Video
import com.sadeghirad.onlinevideo.http.apimodel.customized.ClipModel
import com.sadeghirad.onlinevideo.http.apimodel.customized.VideoDataModel
import com.sadeghirad.onlinevideo.ui.base.IBaseView
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class VideoPresenter(val model: VideoMVP.Model) :
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
        model.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MaybeObserver<Video> {
                override fun onSuccess(t: Video) {

                    val videoDataModel = VideoDataModel()
                    videoDataModel.clips = ArrayList()
                    for (clip in t.clips!!){
                        val clipModel = ClipModel()
                        clipModel.clip = clip
                        videoDataModel.clips!!.add(clipModel)
                    }
                    getView().loadData(videoDataModel)

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
}