package com.sadeghirad.onlinevideo.root

import com.sadeghirad.onlinevideo.http.ApiModule
import com.sadeghirad.onlinevideo.ui.gif.GifFragment
import com.sadeghirad.onlinevideo.ui.gif.GifModule
import com.sadeghirad.onlinevideo.ui.main.MainActivity
import com.sadeghirad.onlinevideo.ui.main.MainModule
import com.sadeghirad.onlinevideo.ui.me.MeFragment
import com.sadeghirad.onlinevideo.ui.me.MeModule
import com.sadeghirad.onlinevideo.ui.video.VideoFragment
import com.sadeghirad.onlinevideo.ui.video.VideoModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, MainModule::class, VideoModule::class, GifModule::class, MeModule::class, ApiModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(videoFragment: VideoFragment)

    fun inject(gifFragment: GifFragment)

    fun inject(meFragment: MeFragment)
}