package com.app.simple.codes.activity.task

import android.Manifest
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.NetworkCapabilities
import android.net.TrafficStats
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.telephony.TelephonyManager
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.core.app.ActivityCompat
import com.app.simple.codes.R
import com.app.simple.codes.activity.TouchActivity
import com.app.simple.codes.utils.P.outI

/**
 * Created by shishoufeng on 2020-05-25.
 * email:shishoufeng1227@126.com
 *
 *
 *  taskAffinity 属性影响 task 任务栈
 *
 */
class SoundActivity :AppCompatActivity(){

    private val TAG = "SoundActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //异步加载布局
        AsyncLayoutInflater(this).inflate(R.layout.activity_task_sound,null,object :AsyncLayoutInflater.OnInflateFinishedListener{
            override fun onInflateFinished(view: View, resid: Int, parent: ViewGroup?) {
                setContentView(view)
            }
        })

//        setContentView(R.layout.activity_task_sound)

//        val bundle = intent.extras?.getBundle("data")
//        val binder = bundle?.getBinder("big_bitmap")
//
//        P.outI(" binder = $binder")
//
//        img_tran.setImageBitmap((binder as BitmapBinder).bitmap)

//        mHandler.sendEmptyMessageDelayed(0,1000)

//        Choreographer.getInstance().postFrameCallback {  }



            TrafficStatsTest()
        networkstatsManagerTest()


    }

    private fun networkstatsManagerTest() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val net = getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

            val telManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

//            if (ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.READ_PHONE_STATE
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                return
//            }
//            net.querySummary(NetworkCapabilities.TRANSPORT_WIFI, telManager.subscriberId,)

        }
    }


    private fun TrafficStatsTest(){
        outI(
            TAG,
            "TotalRxBytes() ${TrafficStats.getTotalRxBytes()}"
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            outI(
                TAG,
                "TotalRxBytes() ${TrafficStats.getUidRxBytes(TrafficStats.getThreadStatsUid())}"
            )
        }
    }



    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            for (i in 1..100){
                val s = Array(10000) { "i$i" }
            }

            sendEmptyMessageDelayed(0,400)
        }
    }



}