package com.pcassem.yunzhuangpei.personal.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.personal.adapter.Child;
import com.pcassem.yunzhuangpei.personal.adapter.MyAdvisoryAdapter;

import java.util.ArrayList;

public class MyAdvisoryActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener, View.OnClickListener {

    ExpandableListView expandableListView;
    MyAdvisoryAdapter adapter;

    private ArrayList<String> groups;
    private ArrayList<ArrayList<Child>> childs;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_advisory);

        initView();
        initTouchEvent();

        groups = new ArrayList<String>();
        groups.add("专家解答");
        groups.add("极速咨询");
        groups.add("现场指导");
        childs = new ArrayList<ArrayList<Child>>();
        for (int i = 0; i < groups.size(); i++) {
            ArrayList<Child> values = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Child child = new Child();
                child.setName("上海宝龙广场项目");
                child.setTime("2017年11月11日上午10:09提交");
                child.setState("未评价");
                values.add(child);
                childs.add(values);
            }
        }

        adapter = new MyAdvisoryAdapter(this, groups, childs);
        expandableListView.setAdapter(adapter);

    }

    private void initView(){
        expandableListView = (ExpandableListView) findViewById(R.id.my_advisory_expandable_view);
        backIv = (ImageView) findViewById(R.id.back_iv);
    }

    private void initTouchEvent(){
        expandableListView.setOnChildClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Intent intent = new Intent(this,MyAdvisoryDetailsActivity.class);
        startActivity(intent);
        return true;
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
