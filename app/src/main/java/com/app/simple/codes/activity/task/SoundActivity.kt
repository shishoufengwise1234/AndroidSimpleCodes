package com.app.simple.codes.activity.task

import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.app.simple.codes.R
import com.app.simple.codes.utils.BitmapBinder
import com.app.simple.codes.utils.P
import kotlinx.android.synthetic.main.activity_task_sound.*

/**
 * Created by shishoufeng on 2020-05-25.
 * email:shishoufeng1227@126.com
 *
 *
 *  taskAffinity 属性影响 task 任务栈
 *
 */
class SoundActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_sound)

//        val bundle = intent.extras?.getBundle("data")
//        val binder = bundle?.getBinder("big_bitmap")
//
//        P.outI(" binder = $binder")
//
//        img_tran.setImageBitmap((binder as BitmapBinder).bitmap)

    }


}