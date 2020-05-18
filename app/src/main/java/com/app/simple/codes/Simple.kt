package com.app.simple.codes

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Created by shishoufeng on 2020-05-16.
 * email:shishoufeng1227@126.com
 *
 *
 * 获取 全局的 context 对象
 *
 */
class SimpleApp : Application() {


    override fun onCreate() {
        super.onCreate()

        context = applicationContext

    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
    }

}