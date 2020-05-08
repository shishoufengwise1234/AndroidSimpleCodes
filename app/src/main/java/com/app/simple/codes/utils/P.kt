package com.app.simple.codes.utils

import android.util.Log

/**
 * Created by shishoufeng on 2020-05-04.
 * email:shishoufeng1227@126.com
 *
 *
 *
 *
 */
object P {

    const val COMMON_TAG = "common_tag"

    fun outI(msg: String){
        outI(COMMON_TAG,msg)
    }

    fun outI(tag:String,msg:String){
        Log.i(tag,msg)
    }
}