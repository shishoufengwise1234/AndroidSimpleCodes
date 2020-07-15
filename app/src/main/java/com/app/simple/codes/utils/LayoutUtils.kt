package com.app.simple.codes.utils

import android.util.Log
import android.view.View

/**
 * Created by shishoufeng on 2020/7/11.
 * email:shishoufeng1227@126.com
 *
 *
 *
 *
 */
object LayoutUtils {





    fun printAllViewLayout(view: View?) {
        if (view == null) {
            return
        }
        //打印view所有的parent
        var parent = view.parent
        while (parent != null) {
            Log.d("printAllViewLayout: ","parent : $parent")

            parent = parent.parent
        }
    }

}