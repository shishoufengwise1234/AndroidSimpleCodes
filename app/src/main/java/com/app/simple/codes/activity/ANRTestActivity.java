package com.app.simple.codes.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by shishoufeng on 2020/7/19.
 * email:shishoufeng1227@126.com
 */
public class ANRTestActivity extends AppCompatActivity {

    private static final String TAG = "ANRTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(() -> {
            synchronized (ANRTestActivity.class){
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        synchronized (ANRTestActivity.class){
            Log.d(TAG, "onCreate: ");
        }
    }
}
