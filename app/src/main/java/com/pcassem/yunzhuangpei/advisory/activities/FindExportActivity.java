package com.pcassem.yunzhuangpei.advisory.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.adapter.ExportListAdapter;
import com.pcassem.yunzhuangpei.advisory.presenter.ExportPresenter;
import com.pcassem.yunzhuangpei.advisory.view.ExportView;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class FindExportActivity extends AppCompatActivity implements ExportView, ExportListAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ExportListAdapter mExportListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ExportPresenter mExportPresenter;
    private List<ExportEntity> mExportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_export);

        initView();

        // 设置布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mExportPresenter = new ExportPresenter(this);
        mExportPresenter.onCreate();
        mExportPresenter.getAllExportList();
    }

    private void initView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.export_recycler_view);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(FindExportActivity.this,ExportDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("specialistID",position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onSuccess(ResultListEntity<ExportEntity> exportList) {
        mExportList = exportList.getResult();
        if (mExportList == null){
            Toast.makeText(FindExportActivity.this, "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mExportListAdapter == null){
            mExportListAdapter = new ExportListAdapter(FindExportActivity.this,mExportList);
            mRecyclerView.setAdapter(mExportListAdapter);
            mExportListAdapter.setmOnItemClickListener(this);
        }
        mExportListAdapter.setmData(mExportList);
    }

    @Override
    public void onError() {
        Toast.makeText(FindExportActivity.this, "网络出错", Toast.LENGTH_SHORT).show();
    }
}
