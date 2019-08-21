package com.sadeghirad.onlinevideo.ui.fullscreen

import dagger.Module
import dagger.Provides

@Module
class  FullscreenModule {

    @Provides
    fun providePresenter():FullscreenMVP.Presenter {
        return FullscreenPresenter()
    }

}