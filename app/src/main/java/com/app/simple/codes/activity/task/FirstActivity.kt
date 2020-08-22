package com.app.simple.codes.activity.task

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.os.Handler
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.TraceCompat
import com.app.simple.codes.R
import com.app.simple.codes.activity.ANRTestActivity
import com.app.simple.codes.activity.TouchActivity
import com.app.simple.codes.precess.ProcessTestActivity
import com.app.simple.codes.utils.LayoutUtils
import com.zhangyue.we.x2c.X2C
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.activity_task_first.*

/**
 * Created by shishoufeng on 2020-05-25.
 * email:shishoufeng1227@126.com
 *
 *
 * 验证 taskAffinity 属性影响 activity task 启动模式
 *
 */
@Xml(layouts = ["activity_task_first"])
class FirstActivity:AppCompatActivity(),View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        //切换theme
//        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        //开启traceview 分析
//        Debug.startMethodTracing("simple")
//        TraceCompat.beginSection("simple_codes")

//        setContentView(R.layout.activity_task_first)
        X2C.setContentView(this,R.layout.activity_task_first)



//        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.thebeautifulswordriver)
//
//        val size = bitmap.allocationByteCount / 1024

//        P.outI("size = $size")

        btn_jump_sound.setOnClickListener {
            val intent = Intent(this,SoundActivity::class.java)
            // 传递 4M 数据大小
//            intent.putExtra("data", ByteArray(4*1024*1024))

//            val bundle = Bundle()
//            val bitmapBinder = BitmapBinder()
//            bitmapBinder.bitmap = bitmap;

//            bundle.putBinder("big_bitmap",bitmapBinder)

//            intent.putExtra("data",bundle)

            startActivity(intent)
        }

        btn_jump_new_process.setOnClickListener {
            val intent = Intent(this,ProcessTestActivity::class.java)
            startActivity(intent)
        }



        Handler().postDelayed({
            LayoutUtils.printAllViewLayout(ll_first_content)
        },3000)


        btn_jump_process_test.setOnClickListener{

        }

        btn_jump_touch_test.setOnClickListener(this)
        btn_jump_anr_test.setOnClickListener(this)


        //停止trace 分析
//        Debug.stopMethodTracing()

        //停止systrace
//        TraceCompat.endSection()

        val time = System.currentTimeMillis()

        val cost = System.currentTimeMillis() - time

        //拿到 CPU 执行时间
        val threadTime = SystemClock.currentThreadTimeMillis()

        //线上场景下 进行 Dump
//        Debug.dumpHprofData("simple_codes_debug")


    }


    override fun onClick(v: View?) {
        val intent = Intent()
        when(v?.id){
            R.id.btn_jump_touch_test ->{
                intent.setClass(this, TouchActivity::class.java)
            }
            R.id.btn_jump_anr_test ->{
                intent.setClass(this,ANRTestActivity::class.java)
            }
        }
        startActivity(intent)
    }


    override fun onResume() {
        super.onResume()


    }
}