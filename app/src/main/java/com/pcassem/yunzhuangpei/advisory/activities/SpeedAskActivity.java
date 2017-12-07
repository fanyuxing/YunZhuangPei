package com.pcassem.yunzhuangpei.advisory.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.pcassem.yunzhuangpei.R;

public class SpeedAskActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_ask);
        initView();
        initTouchEvent();
    }

    private void initView(){
        backIv = (ImageView) findViewById(R.id.back_iv);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
        }
    }
}