package com.sadeghirad.onlinevideo.http.apimodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clip {

    @SerializedName("source")
    @Expose
    var source: String? = null
    @SerializedName("thumb")
    @Expose
    var thumb: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null

    var showVideoAndHideThumbnail:Boolean = false
    var showLoading:Boolean = false
}
