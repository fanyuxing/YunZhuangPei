package com.pcassem.yunzhuangpei.advisory.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.adapter.ExportListAdapter;
import com.pcassem.yunzhuangpei.advisory.adapter.SelectExportListAdapter;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.ArrayList;

public class SelectExportActivity extends AppCompatActivity implements View.OnClickListener, SelectExportListAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private SelectExportListAdapter mSelectExportListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_export);
        initView();

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSelectExportListAdapter = new SelectExportListAdapter(getData());

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置adapter
        mRecyclerView.setAdapter(mSelectExportListAdapter);

        initTouchEvent();

    }

    private void initView(){
        backIv = (ImageView) findViewById(R.id.back_iv);
        mRecyclerView = (RecyclerView) findViewById(R.id.select_export_recycler_view);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
        mSelectExportListAdapter.setmOnItemClickListener(this);
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
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show();
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
