package com.stimednp.roommvvm.utils

import android.util.Log

/**
 * Created by rivaldy on Oct/19/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

object UtilFunctions {
    fun loge(message: String) {
        Log.e("THIS ERROR", "ERROR -> $message")
    }

    fun setTimeStamp() =  System.currentTimeMillis().toString();
}