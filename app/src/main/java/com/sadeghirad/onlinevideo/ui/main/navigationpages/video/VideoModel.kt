package com.sadeghirad.onlinevideo.ui.main.navigationpages.video

import com.sadeghirad.onlinevideo.http.apimodel.Video
import io.reactivex.Maybe

class VideoModel(val repository: IVideoRepository) :
    VideoMVP.Model {
    override fun getData(): Maybe<Video> {
        return repository.getShortClips()
    }

}