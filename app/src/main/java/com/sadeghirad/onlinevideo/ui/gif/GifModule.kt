package com.sadeghirad.onlinevideo.ui.gif

import dagger.Module
import dagger.Provides

@Module
class GifModule {

    @Provides
    fun provideVideoPresenter(): GifMVP.Presenter{
        return GifPresenter()
    }

}