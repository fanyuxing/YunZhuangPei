package com.pcassem.yunzhuangpei.training.activities;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.training.adapter.TabFragmentPagerAdapter;
import com.pcassem.yunzhuangpei.training.fragments.StandardOneFragment;

import java.util.ArrayList;
import java.util.List;

public class StandardActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView tv_item_one;
    private TextView tv_item_two;

    private TextView tv_one_underline;
    private TextView tv_two_underline;


    private ViewPager myViewPager;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        initView();
        initTouchEvent();

        tv_item_one.setOnClickListener(this);
        tv_item_two.setOnClickListener(this);
        myViewPager.setOnPageChangeListener(new MyPagerChangeListener());

        list = new ArrayList<>();
        list.add(new StandardOneFragment());
        list.add(new StandardOneFragment());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);
        checkedStatus(tv_item_one,tv_one_underline);
    }

    private void initView() {
        tv_item_one = (TextView) findViewById(R.id.tv_item_one);
        tv_item_two = (TextView) findViewById(R.id.tv_item_two);

        tv_one_underline = (TextView) findViewById(R.id.tv_one_underline);
        tv_two_underline = (TextView) findViewById(R.id.tv_two_underline);

        myViewPager = (ViewPager) findViewById(R.id.myViewPager);

        backIv = (ImageView) findViewById(R.id.back_iv);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.tv_item_one:
                myViewPager.setCurrentItem(0);
                checkedStatus(tv_item_one,tv_one_underline);
                break;
            case R.id.tv_item_two:
                myViewPager.setCurrentItem(1);
                checkedStatus(tv_item_two,tv_two_underline);
                break;
        }
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
                    checkedStatus(tv_item_one,tv_one_underline);
                    break;
                case 1:
                    checkedStatus(tv_item_two,tv_two_underline);
                    break;
            }
        }
    }

    private void checkedStatus(TextView tv,TextView underline){
        emptyStatus();
        tv.setTextSize(16);
        tv.setTextColor(Color.rgb(19, 56, 109));

        underline.setVisibility(View.VISIBLE);
    }

    private void emptyStatus(){
        tv_item_one.setTextSize(14);
        tv_item_one.setTextColor(Color.rgb(153, 153, 153));
        tv_one_underline.setVisibility(View.GONE);

        tv_item_two.setTextSize(14);
        tv_item_two.setTextColor(Color.rgb(153, 153, 153));
        tv_two_underline.setVisibility(View.GONE);

    }

}
