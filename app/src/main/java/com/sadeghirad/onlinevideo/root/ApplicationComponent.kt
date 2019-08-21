package com.sadeghirad.onlinevideo.root

import com.sadeghirad.onlinevideo.http.ApiModule
import com.sadeghirad.onlinevideo.ui.fullscreen.FullscreenModule
import com.sadeghirad.onlinevideo.ui.fullscreen.FullscreenVideoActivity
import com.sadeghirad.onlinevideo.ui.main.MainActivity
import com.sadeghirad.onlinevideo.ui.main.MainModule
import com.sadeghirad.onlinevideo.ui.main.navigationpages.gif.GifFragment
import com.sadeghirad.onlinevideo.ui.main.navigationpages.gif.GifModule
import com.sadeghirad.onlinevideo.ui.main.navigationpages.me.MeFragment
import com.sadeghirad.onlinevideo.ui.main.navigationpages.me.MeModule
import com.sadeghirad.onlinevideo.ui.main.navigationpages.video.VideoFragment
import com.sadeghirad.onlinevideo.ui.main.navigationpages.video.VideoModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, MainModule::class, VideoModule::class, GifModule::class, MeModule::class, ApiModule::class, FullscreenModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(videoFragment: VideoFragment)

    fun inject(gifFragment: GifFragment)

    fun inject(meFragment: MeFragment)

    fun inject(fullScreenActivity: FullscreenVideoActivity)
}