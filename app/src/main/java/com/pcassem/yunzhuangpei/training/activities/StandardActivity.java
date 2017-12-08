package com.pcassem.yunzhuangpei.training.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.training.adapter.SelectSecondListAdapter;
import com.pcassem.yunzhuangpei.training.adapter.TabFragmentPagerAdapter;
import com.pcassem.yunzhuangpei.training.fragments.StandardOneFragment;
import com.pcassem.yunzhuangpei.utils.AnimationUtil;
import com.pcassem.yunzhuangpei.view.FlowLayout;
import com.pcassem.yunzhuangpei.view.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

public class StandardActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    private static final String TAG = "StandardActivity";

    //tab选择栏实现
    private TextView tv_item_one;
    private TextView tv_item_two;
    private TextView tv_one_underline;
    private TextView tv_two_underline;
    private ViewPager myViewPager;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;

    //伸缩框实现
    private LinearLayout select;
    private LinearLayout selectView;
    private LinearLayout selectFirstContent;
    private LinearLayout selectSecondContent;
    private LinearLayout selectFirstBuinding;
    private LinearLayout selectFirstTunnel;
    private ImageView selectImageView;
    private LinearLayout selectBuilding;
    private LinearLayout selectTunnel;
    private LinearLayout selectRecover;

    String firstStandardList[] = new String[]{"国家标准", "行业标准", "地方标准"};
    String firstStandardDetailsList[][] = new String[][]{
            new String[]{"设计规范", "生产规范", "施工规范", "验收规范"},
            new String[]{"设计规范", "生产规范", "施工规范", "验收规范"},
            new String[]{"设计规范", "生产规范", "施工规范", "验收规范"}};

    String secondStandardList[] = new String[]{"地方政策"};
    String secondStandardDetailsList[][] = new String[][]{
            new String[]{"设计规范", "生产规范", "施工规范", "验收规范"}
    };


    Boolean isTrue = true;


    //选择框内部选择列表实现
    private HorizontalListView selectSecondList;
    private FlowLayout mFlowLayout;
    private LayoutInflater mInflater;
    private SelectSecondListAdapter selectSecondListAdapter;

    private AnimationUtil animationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        initView();
        initTouchEvent();
        tabLayout();

        mInflater = LayoutInflater.from(this);
        animationUtil = AnimationUtil.getInstance(this);
        animationUtil.setHeight(70);

        selectSecondListAdapter = new SelectSecondListAdapter(getApplicationContext(), firstStandardList);
        selectSecondList.setAdapter(selectSecondListAdapter);
        //初始化第一级列表
        selectSecondListAdapter.setSelectedPosition(0);
    }

    //初始化控件
    private void initView() {
        tv_item_one = (TextView) findViewById(R.id.tv_item_one);
        tv_item_two = (TextView) findViewById(R.id.tv_item_two);
        tv_one_underline = (TextView) findViewById(R.id.tv_one_underline);
        tv_two_underline = (TextView) findViewById(R.id.tv_two_underline);
        myViewPager = (ViewPager) findViewById(R.id.myViewPager);
        select = (LinearLayout) findViewById(R.id.navigation_select_btn);
        selectView = (LinearLayout) findViewById(R.id.navigation_select);
        selectFirstContent = (LinearLayout) findViewById(R.id.select_first_content);
        selectSecondContent = (LinearLayout) findViewById(R.id.select_second_content);
        selectFirstBuinding = (LinearLayout) findViewById(R.id.select_first_buinding);
        selectFirstTunnel = (LinearLayout) findViewById(R.id.select_first_tunnel);
        selectImageView = (ImageView) findViewById(R.id.select_image_view);
        selectBuilding = (LinearLayout) findViewById(R.id.select_buinding);
        selectTunnel = (LinearLayout) findViewById(R.id.select_tunnel);
        selectSecondList = (HorizontalListView) findViewById(R.id.select_second_select);
        mFlowLayout = (FlowLayout) findViewById(R.id.flow_layout);
        selectRecover = (LinearLayout) findViewById(R.id.select_recover);
    }

    //监听事件
    private void initTouchEvent() {
        selectSecondList.setOnItemClickListener(this);
        myViewPager.setOnPageChangeListener(new MyPagerChangeListener());
        tv_item_one.setOnClickListener(this);
        tv_item_two.setOnClickListener(this);
        selectFirstBuinding.setOnClickListener(this);
        selectFirstTunnel.setOnClickListener(this);
        select.setOnClickListener(this);
        selectRecover.setOnClickListener(this);

    }


    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigation_select_btn:
                if (selectView.getVisibility() == View.GONE) {
                    animationUtil.animateOpen(selectView);
                    selectView.setVisibility(View.VISIBLE);
                    selectFirstContent.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.select_first_buinding:
                selectImageView.setVisibility(View.GONE);
                selectBuilding.setVisibility(View.VISIBLE);
                selectTunnel.setVisibility(View.GONE);

                selectFirstContent.setVisibility(View.GONE);
                selectSecondContent.setVisibility(View.VISIBLE);

                selectSecondListAdapter.setSelectedPosition(0);
                if (isTrue) {
                    //改变选择控件的高度
                    animationUtil.modifyHight(selectView, 0, firstStandardDetailsList);
                    //初始化最后一级选择标签
                    popularTagsData(firstStandardDetailsList[0]);
                } else {
                    animationUtil.modifyHight(selectView, 0, secondStandardDetailsList);
                    popularTagsData(secondStandardDetailsList[0]);
                }
                break;

            case R.id.select_first_tunnel:
                selectImageView.setVisibility(View.GONE);
                selectBuilding.setVisibility(View.GONE);
                selectTunnel.setVisibility(View.VISIBLE);

                selectFirstContent.setVisibility(View.GONE);
                selectSecondContent.setVisibility(View.VISIBLE);

                selectSecondListAdapter.setSelectedPosition(0);
                if (isTrue) {
                    //改变选择控件的高度
                    animationUtil.modifyHight(selectView, 0, firstStandardDetailsList);
                    //初始化最后一级选择标签
                    popularTagsData(firstStandardDetailsList[0]);
                } else {
                    animationUtil.modifyHight(selectView, 0, secondStandardDetailsList);
                    popularTagsData(secondStandardDetailsList[0]);
                }
                break;
            case R.id.select_recover:
                selectImageView.setVisibility(View.VISIBLE);
                selectBuilding.setVisibility(View.GONE);
                selectTunnel.setVisibility(View.GONE);

                selectFirstContent.setVisibility(View.GONE);
                selectSecondContent.setVisibility(View.GONE);

                animationUtil.animateClose(selectView);
                break;

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectSecondListAdapter.setSelectedPosition(position);

        //根据选择改变选择控件的高度
        if (isTrue) {
            animationUtil.modifyHight(selectView, position, firstStandardDetailsList);
            popularTagsData(firstStandardDetailsList[position]);
        } else {
            animationUtil.modifyHight(selectView, position, secondStandardDetailsList);
            popularTagsData(secondStandardDetailsList[position]);
        }

    }

    private void popularTagsData(final String[] data) {
        mFlowLayout.removeAllViews();
        for (int i = 0; i < data.length; i++) {
            final TextView tv = (TextView) mInflater.inflate(R.layout.item_select_tags, mFlowLayout, false);
            tv.setText(data[i]);
            tv.setTag(i);
            //添加到父View
            mFlowLayout.addView(tv);
            mFlowLayout.setOnItemClickClick(new FlowLayout.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    Toast.makeText(StandardActivity.this, ((TextView)mFlowLayout.getChildAt(position)).getMeasuredWidth() + "", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    //最新培训、全部切换实现
    public void tabLayout() {
        list = new ArrayList<>();
        list.add(new StandardOneFragment());
        list.add(new StandardOneFragment());
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

                    isTrue = true;
                    animationUtil.modifyHight(selectView, 0, firstStandardDetailsList);
                    selectSecondListAdapter.setStandard(firstStandardList);
                    selectSecondList.setAdapter(selectSecondListAdapter);
                    //初始化第一级列表
                    selectSecondListAdapter.setSelectedPosition(0);
                    popularTagsData(firstStandardDetailsList[0]);

                    break;
                case 1:
                    checkedStatus(tv_item_two, tv_two_underline);
                    isTrue = false;
                    animationUtil.modifyHight(selectView, 0, secondStandardDetailsList);
                    selectSecondListAdapter.setStandard(secondStandardList);
                    selectSecondList.setAdapter(selectSecondListAdapter);
                    //初始化第一级列表
                    selectSecondListAdapter.setSelectedPosition(0);
                    popularTagsData(secondStandardDetailsList[0]);

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
