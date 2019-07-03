package com.sadeghirad.onlinevideo.ui.me

import dagger.Module
import dagger.Provides

@Module
class MeModule {

    @Provides
    fun provideVideoPresenter(): MeMVP.Presenter{
        return MePresenter()
    }

}