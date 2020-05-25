package com.app.simple.codes

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.app.simple.codes.utils.P

/**
 * Created by shishoufeng on 2020-05-16.
 * email:shishoufeng1227@126.com
 *
 *
 * 获取 全局的 context 对象
 *
 */
class SimpleApp : Application() {

    private val TAG = "SimpleApp"

    override fun onCreate() {
        super.onCreate()

        P.outI(TAG,"onCreate()")
        P.outI(TAG,"onCreate()  processName = "+getAppProcessName())



        context = applicationContext

    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
    }

    fun getAppProcessName(): String {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (a in am.runningAppProcesses){
            if(a.pid == android.os.Process.myPid()){
                return a.processName
            }
        }
        return ""
    }

}