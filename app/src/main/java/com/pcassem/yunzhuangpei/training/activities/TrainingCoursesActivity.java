package com.pcassem.yunzhuangpei.training.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.training.adapter.TabFragmentPagerAdapter;
import com.pcassem.yunzhuangpei.training.fragments.TrainingCoursesFragment;

import java.util.ArrayList;
import java.util.List;

public class TrainingCoursesActivity extends ActionBarActivity implements View.OnClickListener {


    private static final String TAG = "TrainingCourseActivity";

    //tab选择栏实现
    private TextView tv_item_one;
    private TextView tv_item_two;
    private TextView tv_one_underline;
    private TextView tv_two_underline;
    private ViewPager myViewPager;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_courses);

        initView();
        initTouchEvent();
        tabLayout();

    }

    //初始化控件
    private void initView() {
        tv_item_one = (TextView) findViewById(R.id.tv_item_one);
        tv_item_two = (TextView) findViewById(R.id.tv_item_two);
        tv_one_underline = (TextView) findViewById(R.id.tv_one_underline);
        tv_two_underline = (TextView) findViewById(R.id.tv_two_underline);
        myViewPager = (ViewPager) findViewById(R.id.myViewPager);
    }

    //监听事件
    private void initTouchEvent() {
        myViewPager.setOnPageChangeListener(new MyPagerChangeListener());
        tv_item_one.setOnClickListener(this);
        tv_item_two.setOnClickListener(this);
    }


    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_item_one:
                myViewPager.setCurrentItem(0);
                checkedStatus(tv_item_one, tv_one_underline);
                break;
            case R.id.tv_item_two:
                myViewPager.setCurrentItem(1);
                checkedStatus(tv_item_two, tv_two_underline);
                break;
        }
    }


    //最新培训、全部切换实现
    public void tabLayout() {
        list = new ArrayList<>();
        list.add(new TrainingCoursesFragment());
        list.add(new TrainingCoursesFragment());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);
        checkedStatus(tv_item_one, tv_one_underline);
    }

    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    checkedStatus(tv_item_one, tv_one_underline);
                    break;
                case 1:
                    checkedStatus(tv_item_two, tv_two_underline);
                    break;
            }
        }
    }

    private void checkedStatus(TextView tv, TextView underline) {
        emptyStatus();
        tv.setTextSize(16);
        tv.setTextColor(Color.rgb(19, 56, 109));

        underline.setVisibility(View.VISIBLE);
    }

    private void emptyStatus() {
        tv_item_one.setTextSize(14);
        tv_item_one.setTextColor(Color.rgb(153, 153, 153));
        tv_one_underline.setVisibility(View.GONE);

        tv_item_two.setTextSize(14);
        tv_item_two.setTextColor(Color.rgb(153, 153, 153));
        tv_two_underline.setVisibility(View.GONE);

    }

}
