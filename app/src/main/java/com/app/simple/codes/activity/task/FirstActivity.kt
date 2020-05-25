package com.app.simple.codes.activity.task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.simple.codes.R
import com.app.simple.codes.precess.ProcessTestActivity
import kotlinx.android.synthetic.main.activity_task_first.*

/**
 * Created by shishoufeng on 2020-05-25.
 * email:shishoufeng1227@126.com
 *
 *
 * 验证 taskAffinity 属性影响 activity task 启动模式
 *
 */
class FirstActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_first)

        btn_jump_sound.setOnClickListener {
            val intent = Intent(this,SoundActivity::class.java)
            // 传递 4M 数据大小
//            intent.putExtra("data", ByteArray(4*1024*1024))

            startActivity(intent)
        }

        btn_jump_new_process.setOnClickListener {
            val intent = Intent(this,ProcessTestActivity::class.java)
            startActivity(intent)
        }
    }
}