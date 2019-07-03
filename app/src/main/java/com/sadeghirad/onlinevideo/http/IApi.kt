package com.sadeghirad.onlinevideo.http

import com.sadeghirad.onlinevideo.http.apimodel.Video
import io.reactivex.Maybe
import retrofit2.http.GET


interface IApi {

    @GET("/bins/da95b/")
    fun getShortClips(): Maybe<Video>

}