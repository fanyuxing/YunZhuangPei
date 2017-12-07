package com.pcassem.yunzhuangpei.personal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.personal.adapter.Child;
import com.pcassem.yunzhuangpei.personal.adapter.MyAdvisoryAdapter;
import com.pcassem.yunzhuangpei.personal.adapter.MyAdvisoryDetailsAdapter;

import java.util.ArrayList;

public class MyAdvisoryDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ExpandableListView expandableListView;
    MyAdvisoryDetailsAdapter adapter;

    private ArrayList<String> groups;
    private ArrayList<ArrayList<String>> childs;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_advisory_details);

        initView();
        initTouchEvent();

        groups = new ArrayList<String>();
        groups.add("问题信息");
        groups.add("专家答复");
        childs = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < groups.size(); i++) {
            ArrayList<String> values = new ArrayList<>();
                values.add("nihao");
                childs.add(values);
        }

        expandableListView = (ExpandableListView) findViewById(R.id.my_advisory_details_expandable_view);

        adapter = new MyAdvisoryDetailsAdapter(this, groups, childs);

        expandableListView.setAdapter(adapter);

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
