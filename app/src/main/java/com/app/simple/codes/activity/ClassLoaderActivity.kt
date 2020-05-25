package com.app.simple.codes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.simple.codes.R
import com.app.simple.codes.utils.P

/**
 * Created by shishoufeng on 2020-05-16.
 * email:shishoufeng1227@126.com
 *
 *
 *
 *
 */
class ClassLoaderActivity :AppCompatActivity(){

    private val TAG = "ClassLoaderActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_loader)


        val c = classLoader

        P.outI(TAG,"c = $c")
        P.outI(TAG,"c parent = ${c.parent}")

    }

}