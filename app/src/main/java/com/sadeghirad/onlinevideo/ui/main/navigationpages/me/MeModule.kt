package com.sadeghirad.onlinevideo.ui.main.navigationpages.me

import dagger.Module
import dagger.Provides

@Module
class MeModule {

    @Provides
    fun provideVideoPresenter(): MeMVP.Presenter {
        return MePresenter()
    }

}