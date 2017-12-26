package com.pcassem.yunzhuangpei;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import io.rong.imkit.RongIM;

/**
 * Created by zhangqi on 2017/12/10.
 */

public class MyApplication extends Application {

    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"=58690f64");
        RongIM.init(this);

        mSharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove("username");
        editor.commit();
    }
}
