package com.pcassem.yunzhuangpei.advisory.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.adapter.ExportListAdapter;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.ArrayList;

public class FindExportActivity extends AppCompatActivity implements ExportListAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ExportListAdapter mExportListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_export);

        initView();
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mExportListAdapter = new ExportListAdapter(getData());

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置adapter
        mRecyclerView.setAdapter(mExportListAdapter);
        mExportListAdapter.setmOnItemClickListener(this);
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 20; i++) {
            data.add(i + temp);
        }
        return data;
    }

    private void initView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.export_recycler_view);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, getData().get(position), Toast.LENGTH_SHORT).show();
    }
}
