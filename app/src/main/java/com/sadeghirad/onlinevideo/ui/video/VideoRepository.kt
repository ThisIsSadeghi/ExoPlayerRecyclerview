package com.sadeghirad.onlinevideo.ui.video

import com.sadeghirad.onlinevideo.http.IApi
import com.sadeghirad.onlinevideo.http.apimodel.Video
import io.reactivex.Maybe



class VideoRepository(val apiService: IApi) : IVideoRepository {
    override fun getShortClips(): Maybe<Video> {

        return apiService.getShortClips()

    }

}