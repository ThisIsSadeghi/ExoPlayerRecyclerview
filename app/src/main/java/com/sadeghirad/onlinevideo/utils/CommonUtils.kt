package com.sadeghirad.onlinevideo.utils

import android.os.Build

class CommonUtils {
    companion object {

        fun isMarshmallowOrHigher(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        }


    }
}