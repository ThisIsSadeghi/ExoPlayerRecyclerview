package com.sadeghirad.onlinevideo.root

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class ApplicationModule(private val applicationContext: Application) {


    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return applicationContext
    }
}
