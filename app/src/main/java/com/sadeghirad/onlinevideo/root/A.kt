package com.sadeghirad.onlinevideo.root

import android.app.Application
import com.sadeghirad.onlinevideo.http.ApiModule
import com.sadeghirad.onlinevideo.ui.fullscreen.FullscreenModule
import com.sadeghirad.onlinevideo.ui.main.MainModule
import com.sadeghirad.onlinevideo.ui.main.navigationpages.gif.GifModule
import com.sadeghirad.onlinevideo.ui.main.navigationpages.me.MeModule
import com.sadeghirad.onlinevideo.ui.main.navigationpages.video.VideoModule

class A : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .mainModule(MainModule())
            .videoModule(VideoModule())
            .gifModule(GifModule())
            .meModule(MeModule())
            .apiModule(ApiModule())
            .fullscreenModule(FullscreenModule())
            .build()
    }
}
