package com.sadeghirad.onlinevideo.ui.main

import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainPresenter(): MainMVP.Presenter {
        return MainPresenter()
    }

}