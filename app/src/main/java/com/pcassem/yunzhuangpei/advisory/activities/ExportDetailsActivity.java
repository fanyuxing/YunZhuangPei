package com.pcassem.yunzhuangpei.advisory.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.view.FlowLayout;

public class ExportDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] mVals = new String[] { "防水", "构件安装", "开裂", "管廊施工"};
    private LayoutInflater mInflater;
    private FlowLayout mFlowLayout;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_details);
        initView();
        initTouchEvent();

        popularTagsData();
    }

    private void initView(){
        mInflater = LayoutInflater.from(this);
        backIv = (ImageView) findViewById(R.id.back_iv);
        mFlowLayout = (FlowLayout) findViewById(R.id.flowlayout_popular_tags);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
    }

    private void popularTagsData(){
        for (int i = 0; i < mVals.length; i++) {
            TextView tv = (TextView) mInflater.inflate(
                    R.layout.item_advisory_export_tags, mFlowLayout, false);
            tv.setText(mVals[i]);
            //添加到父View
            mFlowLayout.addView(tv);
        }
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
