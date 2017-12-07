package com.pcassem.yunzhuangpei.advisory.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pcassem.yunzhuangpei.R;

public class SiteGuideActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mGuideSelectExport;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_guide);
        initView();
        initTouchEvent();
    }

    private void initView(){
        mGuideSelectExport = (RelativeLayout) findViewById(R.id.guide_select_export);
        backIv = (ImageView) findViewById(R.id.back_iv);
    }

    private void initTouchEvent(){
        mGuideSelectExport.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.guide_select_export:
                Intent intent = new Intent(SiteGuideActivity.this,SelectExportActivity.class);
                startActivity(intent);
                break;
        }
    }
}
