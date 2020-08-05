package com.app.simple.codes.precess

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.simple.codes.R
import com.zhangyue.we.x2c.X2C
import com.zhangyue.we.x2c.ano.Xml

/**
 * Created by shishoufeng on 2020-05-25.
 * email:shishoufeng1227@126.com
 *
 *
 * 验证 启动跨进程 activity application 会不会重新创建
 *
 */
@Xml(layouts = ["activity_process_test"])
class ProcessTestActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        X2C.setContentView(this,R.layout.activity_process_test)


    }

}