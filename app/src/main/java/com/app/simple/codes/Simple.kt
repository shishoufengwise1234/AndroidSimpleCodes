package com.app.simple.codes

import android.R.attr
import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import android.util.Log
import com.app.simple.codes.utils.P
import com.github.anrwatchdog.ANRError
import com.github.anrwatchdog.ANRWatchDog
import com.github.moduth.blockcanary.BlockCanary
import com.tencent.mmkv.MMKV


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

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)


    }

    override fun onCreate() {

        if (BuildConfig.DEBUG) {
            //检测线程
            StrictMode.setThreadPolicy(
                ThreadPolicy.Builder()
                    .permitAll()
                    .penaltyLog()
                    .build()
            )
            //检测虚拟机
            StrictMode.setVmPolicy(
                VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
        super.onCreate()





        P.outI(TAG,"onCreate()")
        P.outI(TAG,"onCreate()  processName = "+getAppProcessName())



        context = applicationContext

        // Do it on main process
        // Do it on main process
        BlockCanary.install(this, AppBlockCanaryContext()).start()


        //ANR 监控组件
        ANRWatchDog().setANRInterceptor { duration ->
            val ret: Long = 5000 - duration
            if (ret > 0) {
                Log.w(
                    TAG,
                    "Intercepted ANR that is too short (" + attr.duration + " ms), postponing for " + ret + " ms."
                )
            }
            ret
        }.setANRListener {ANRError ->
                Log.d(TAG, "onCreate: $ANRError")
            }
            .setReportAllThreads()
            .start()

        MMKV.initialize(this)


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