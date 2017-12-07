package com.pcassem.yunzhuangpei.advisory.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.activities.ExportAnswerActivity;
import com.pcassem.yunzhuangpei.advisory.activities.ExportDetailsActivity;
import com.pcassem.yunzhuangpei.advisory.activities.FindExportActivity;
import com.pcassem.yunzhuangpei.advisory.activities.SiteGuideActivity;
import com.pcassem.yunzhuangpei.advisory.activities.SpeedAskActivity;
import com.pcassem.yunzhuangpei.advisory.adapter.ExportListAdapter;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;
import com.pcassem.yunzhuangpei.home.fragments.HomeFragment;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvisoryFragment extends Fragment implements View.OnClickListener, ExportListAdapter.OnItemClickListener {

    private LinearLayout mExportAnswer;
    private LinearLayout mSpeedAsk;
    private LinearLayout mSiteGudie;
    private LinearLayout mFindExport;
    private TextView mAllExports;

    private RecyclerView mRecyclerView;
    private ExportListAdapter mExportListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public static AdvisoryFragment newInstance() {
        AdvisoryFragment advisoryFragment = new AdvisoryFragment();
        return advisoryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advisory, container, false);
        initView(view);
        initTouchEvent();

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mExportListAdapter = new ExportListAdapter(getData());

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置adapter
        mRecyclerView.setAdapter(mExportListAdapter);

        mExportListAdapter.setmOnItemClickListener(this);
        return view;
    }

    private void initView(View view){
        mExportAnswer = (LinearLayout) view.findViewById(R.id.advisory_export_answer);
        mSpeedAsk = (LinearLayout) view.findViewById(R.id.advisory_speed_ask);
        mSiteGudie = (LinearLayout) view.findViewById(R.id.advisory_site_guide);
        mFindExport = (LinearLayout) view.findViewById(R.id.advisory_find_export);

        mAllExports = (TextView) view.findViewById(R.id.all_exports);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.export_recycler_view);
    }

    private void initTouchEvent(){
        mExportAnswer.setOnClickListener(this);
        mSpeedAsk.setOnClickListener(this);
        mSiteGudie.setOnClickListener(this);
        mFindExport.setOnClickListener(this);
        mAllExports.setOnClickListener(this);
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
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), ExportDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.advisory_export_answer:
                Intent intent1= new Intent(getActivity(), ExportAnswerActivity.class);
                startActivity(intent1);
                break;
            case R.id.advisory_speed_ask:
                Intent intent2 = new Intent(getActivity(), SpeedAskActivity.class);
                startActivity(intent2);
                break;
            case R.id.advisory_site_guide:
                Intent intent3 = new Intent(getActivity(), SiteGuideActivity.class);
                startActivity(intent3);
                break;
            case R.id.advisory_find_export:
                Intent intent4 = new Intent(getActivity(), FindExportActivity.class);
                startActivity(intent4);
                break;
            case R.id.all_exports:
                Intent intent5 = new Intent(getActivity(), FindExportActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
