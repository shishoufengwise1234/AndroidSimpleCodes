package com.app.simple.codes.hotfix

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.simple.codes.R
import com.app.simple.codes.SimpleApp
import kotlinx.android.synthetic.main.activity_simple_hotfix.*

/**
 * Created by shishoufeng on 2020-05-16.
 * email:shishoufeng1227@126.com
 *
 *
 *
 *
 */
class HotFixActivity :AppCompatActivity(){

    private lateinit var iHotFix: IHotFix

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_hotfix)

        iHotFix = HotException()

        btnHotFix.setOnClickListener {

            Toast.makeText(SimpleApp.context,iHotFix.doFix(),Toast.LENGTH_SHORT).show()
        }



    }
}