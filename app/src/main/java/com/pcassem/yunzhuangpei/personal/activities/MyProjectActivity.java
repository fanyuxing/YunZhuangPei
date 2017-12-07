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
import com.pcassem.yunzhuangpei.personal.adapter.MyProjectAdapter;
import com.pcassem.yunzhuangpei.view.NestedExpandaleListView;

import java.util.ArrayList;

public class MyProjectActivity extends AppCompatActivity implements View.OnClickListener {

    NestedExpandaleListView expandableListView;
    MyProjectAdapter adapter;

    private ArrayList<String> groups;
    private ArrayList<ArrayList<String>> childs;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);

        initView();
        initTouchEvent();

        groups = new ArrayList<String>();
        groups.add("上海宝龙广场项目");
        groups.add("长沙旭辉国际广场项目");
        childs = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < groups.size(); i++) {
            ArrayList<String> values = new ArrayList<>();
            values.add("nihao");
            childs.add(values);
        }

        expandableListView = (NestedExpandaleListView) findViewById(R.id.my_project_expandable_view);

        adapter = new MyProjectAdapter(this, groups, childs);

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
