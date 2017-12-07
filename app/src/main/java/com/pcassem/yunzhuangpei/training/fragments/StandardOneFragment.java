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
import com.pcassem.yunzhuangpei.training.adapter.StandardOneAdapter;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.ArrayList;


public class StandardOneFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private StandardOneAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_standard_one,container,false);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new StandardOneAdapter(getData());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setmOnItemClickListener(new StandardOneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 3; i++) {
            data.add(i + temp);
        }

        return data;
    }

}
