package com.pcassem.yunzhuangpei.training.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.home.activities.NewsDetailsActivity;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.ArrayList;

public class ProjectCasesFragment extends Fragment implements LatestNewsAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private LatestNewsAdapter mLatestNewsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_cases,container,false);
        initView(view);

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

    private void initView(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 10; i++) {
            data.add(i + temp);
        }
        return data;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
        startActivity(intent);
    }
}
