package com.pcassem.yunzhuangpei.home.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.activities.ExportAnswerActivity;
import com.pcassem.yunzhuangpei.advisory.activities.SiteGuideActivity;
import com.pcassem.yunzhuangpei.advisory.activities.SpeedAskActivity;
import com.pcassem.yunzhuangpei.home.activities.AllNewsActivity;
import com.pcassem.yunzhuangpei.home.activities.HomeSearchActivity;
import com.pcassem.yunzhuangpei.home.activities.NewsDetailsActivity;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;
import com.pcassem.yunzhuangpei.training.activities.ProblemSolvingActivity;
import com.pcassem.yunzhuangpei.training.activities.ProcessActivity;
import com.pcassem.yunzhuangpei.training.activities.TrainingCoursesActivity;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener, LatestNewsAdapter.OnItemClickListener {

    private RelativeLayout mHomeSearchBtn;
    private TextView mAllNewsJumpBtn;

    private RecyclerView mRecyclerView;
    private LatestNewsAdapter mLatestNewsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button button1;
    private Button button2;

    private LinearLayout button3;
    private LinearLayout button4;
    private LinearLayout button5;
    private LinearLayout button6;


    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);
        initTouchEvent();

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mLatestNewsAdapter = new LatestNewsAdapter(getData());

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置adapter
        mRecyclerView.setAdapter(mLatestNewsAdapter);
        mLatestNewsAdapter.setmOnItemClickListener(this);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mHomeSearchBtn = (RelativeLayout) view.findViewById(R.id.home_search_btn);
        mAllNewsJumpBtn = (TextView) view.findViewById(R.id.jump_all_news_activity);

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);

        button3 = (LinearLayout) view.findViewById(R.id.button3);
        button4 = (LinearLayout) view.findViewById(R.id.button4);
        button5 = (LinearLayout) view.findViewById(R.id.button5);
        button6 = (LinearLayout) view.findViewById(R.id.button6);
    }

    private void initTouchEvent() {
        mHomeSearchBtn.setOnClickListener(this);
        mAllNewsJumpBtn.setOnClickListener(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 20; i++) {
            data.add(i + temp);
        }
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_search_btn:
                Intent searchIntent = new Intent(getActivity(), HomeSearchActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.jump_all_news_activity:
                Intent jumpAllNewsIntent = new Intent(getActivity(), AllNewsActivity.class);
                startActivity(jumpAllNewsIntent);
                break;
            case R.id.button1:
                Intent intent = new Intent(getActivity(), SpeedAskActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(getActivity(), TrainingCoursesActivity.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(getActivity(), ProcessActivity.class);
                startActivity(intent2);
                break;
            case R.id.button4:
                Intent intent3 = new Intent(getActivity(), ProblemSolvingActivity.class);
                startActivity(intent3);
                break;
            case R.id.button5:
                Intent intent4 = new Intent(getActivity(), ExportAnswerActivity.class);
                startActivity(intent4);
                break;
            case R.id.button6:
                Intent intent5 = new Intent(getActivity(), SiteGuideActivity.class);
                startActivity(intent5);
                break;
        }
    }

    //点击最新资讯进入单个资讯
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
        startActivity(intent);
    }
}
