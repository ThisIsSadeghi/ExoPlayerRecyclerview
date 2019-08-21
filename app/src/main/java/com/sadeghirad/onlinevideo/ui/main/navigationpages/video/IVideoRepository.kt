package com.sadeghirad.onlinevideo.ui.main.navigationpages.video

import com.sadeghirad.onlinevideo.http.apimodel.Video
import io.reactivex.Maybe

interface IVideoRepository {
    fun getShortClips():Maybe<Video>
}