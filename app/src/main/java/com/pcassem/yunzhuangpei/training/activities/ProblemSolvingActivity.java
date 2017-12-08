package com.pcassem.yunzhuangpei.training.activities;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.training.adapter.SelectSecondListAdapter;
import com.pcassem.yunzhuangpei.training.adapter.SelectThirdListAdapter;
import com.pcassem.yunzhuangpei.training.adapter.TabFragmentPagerAdapter;
import com.pcassem.yunzhuangpei.training.fragments.StandardOneFragment;
import com.pcassem.yunzhuangpei.utils.AnimationUtil;
import com.pcassem.yunzhuangpei.view.FlowLayout;
import com.pcassem.yunzhuangpei.view.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

public class ProblemSolvingActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


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


    String firstProblemList[] = new String[]{"滲水", "开裂", "节点处理", "构建安装", "其它"};
    String firstProblemDetailsList[][] = new String[][]{
            new String[]{"卫生间", "阳台", "厨房", "屋面", "外墙", "其它"},
            new String[]{"外挂板", "外墙板", "内、隔墙板", "叠合楼板", "其它"},
            new String[]{"主次梁", "梁、柱", "梁、板", "剪力墙、柱", "内、隔墙", "其它"},
            new String[]{"外挂版", "外墙板", "内、隔墙", "叠合梁", "叠合板", "其它"},
            new String[]{},

    };


    String firstConstructionList[] = new String[]{"施工前准备阶段", "施工阶段", "验收阶段"};
    String secondConstructionList[][] = new String[][]{
            new String[]{"设计视图", "施工策划", "施工工具"},
            new String[]{"基础阶段", "主题阶段", "装修阶段"},
            new String[]{"验收流程", "验收资料"}};
    String thirdConstructionList[][][] = new String[][][]{
            new String[][]{
                    new String[]{"工艺方面", "建筑结构"},
                    new String[]{"施工组织策划", "专项施工方案"},
                    new String[]{"工具", "机械设备"}
            },
            new String[][]{
                    new String[]{"质量验收", "结合层"},
                    new String[]{"分项施工工艺", "施工节点"},
                    new String[]{"屋面防水", "外墙防水", "内墙拼缝处理"}
            },
            new String[][]{
                    new String[]{},
                    new String[]{},
                    new String[]{}
            }
    };


    Boolean isTrue = true;

    //选择框内部选择列表实现
    private HorizontalListView selectSecondList;
    private HorizontalListView selectThirdList;
    private FlowLayout mFlowLayout;
    private LayoutInflater mInflater;
    private SelectSecondListAdapter selectSecondListAdapter;
    private SelectThirdListAdapter selectThirdListAdapter;

    private AnimationUtil animationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_solving);

        initView();
        initTouchEvent();
        tabLayout();

        mInflater = LayoutInflater.from(this);
        animationUtil = AnimationUtil.getInstance(this);
        animationUtil.setHeight(100);

        selectThirdList.setVisibility(View.GONE);
        selectSecondListAdapter = new SelectSecondListAdapter(getApplicationContext(), firstProblemList);
        selectSecondList.setAdapter(selectSecondListAdapter);
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
        selectThirdList = (HorizontalListView) findViewById(R.id.select_third_select);
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

                if (isTrue) {
                    selectThirdList.setVisibility(View.GONE);
                    selectSecondListAdapter.setSelectedPosition(0);
                    //初始化最后一级选择标签
                    popularTagsData(firstProblemDetailsList[0]);
                } else {
                    selectThirdList.setVisibility(View.VISIBLE);
                    setSubList(0);
                }
                break;

            case R.id.select_first_tunnel:
                selectImageView.setVisibility(View.GONE);
                selectBuilding.setVisibility(View.GONE);
                selectTunnel.setVisibility(View.VISIBLE);

                selectFirstContent.setVisibility(View.GONE);
                selectSecondContent.setVisibility(View.VISIBLE);

                if (isTrue) {
                    selectThirdList.setVisibility(View.GONE);
                    selectSecondListAdapter.setSelectedPosition(0);
                    //初始化最后一级选择标签
                    popularTagsData(firstProblemDetailsList[0]);
                } else {
                    selectThirdList.setVisibility(View.VISIBLE);
                    setSubList(0);
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
            selectThirdList.setVisibility(View.GONE);
            popularTagsData(firstProblemDetailsList[position]);
        } else {
            selectThirdList.setVisibility(View.VISIBLE);
            setSubList(position);
        }

    }

    public void setSubList(int position) {
        final int location = position;
        selectSecondListAdapter = new SelectSecondListAdapter(getApplicationContext(), firstConstructionList);
        selectSecondList.setAdapter(selectSecondListAdapter);
        selectSecondListAdapter.setSelectedPosition(position);
        selectSecondListAdapter.notifyDataSetInvalidated();

        selectThirdListAdapter = new SelectThirdListAdapter(ProblemSolvingActivity.this, secondConstructionList, position);
        selectThirdList.setAdapter(selectThirdListAdapter);

        //初始化第三层列表展示
        selectThirdListAdapter.setSelectedPosition(0);
        popularTagsData(thirdConstructionList[0][0]);

        //第三层点击事件
        selectThirdList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                selectThirdListAdapter.setSelectedPosition(pos);
                popularTagsData(thirdConstructionList[location][pos]);
            }
        });
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
                    Toast.makeText(ProblemSolvingActivity.this, ((TextView) mFlowLayout.getChildAt(position)).getMeasuredWidth() + "", Toast.LENGTH_SHORT).show();
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
                    selectThirdList.setVisibility(View.GONE);
                    selectSecondListAdapter = new SelectSecondListAdapter(getApplicationContext(), firstProblemList);
                    selectSecondList.setAdapter(selectSecondListAdapter);
                    selectSecondListAdapter.setSelectedPosition(0);
                    break;
                case 1:
                    checkedStatus(tv_item_two, tv_two_underline);
                    selectThirdList.setVisibility(View.VISIBLE);
                    isTrue = false;
                    setSubList(0);
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
