package com.app.simple.codes.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.simple.codes.R;
import com.app.simple.codes.manager.CallBackManager;
import com.app.simple.codes.manager.OnCallBackListener;
import com.app.simple.codes.utils.P;
import com.zhangyue.we.x2c.X2C;
import com.zhangyue.we.x2c.ano.Xml;

/**
 * Created by shishoufeng on 2020-05-08.
 * email:shishoufeng1227@126.com
 *
 */
@Xml(layouts = "activity_touch_test")
public class TouchActivity extends AppCompatActivity implements OnCallBackListener {

    private static final String TAG = "TouchActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        X2C.setContentView(this, R.layout.activity_touch_test);




        CallBackManager.Companion.getInstance().addCallBack(this);


    }



    @Override
    public void doFeature() {

    }
}
