package com.sadeghirad.onlinevideo.http.apimodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Video {

    @SerializedName("clips")
    @Expose
    var clips: List<Clip>? = null

}
