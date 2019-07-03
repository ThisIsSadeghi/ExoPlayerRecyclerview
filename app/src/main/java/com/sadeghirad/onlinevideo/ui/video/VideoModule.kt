package com.sadeghirad.onlinevideo.ui.video

import com.sadeghirad.onlinevideo.http.ApiModule
import com.sadeghirad.onlinevideo.http.IApi
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class VideoModule {

    @Provides
    fun provideVideoPresenter(model: VideoMVP.Model): VideoMVP.Presenter {
        return VideoPresenter(model)
    }

    @Provides
    fun provideVideoModel(repository: IVideoRepository): VideoMVP.Model {
        return VideoModel(repository)
    }

    @Provides
    fun provideVideoRepository(apiService: IApi): IVideoRepository {
        return VideoRepository(apiService)
    }

}