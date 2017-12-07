package com.pcassem.yunzhuangpei.home.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.ArrayList;

public class AllNewsActivity extends AppCompatActivity implements View.OnClickListener, LatestNewsAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private LatestNewsAdapter mLatestNewsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);

        initView();
        initTouchEvent();

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLatestNewsAdapter = new LatestNewsAdapter(getData());

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置adapter
        mRecyclerView.setAdapter(mLatestNewsAdapter);
        mLatestNewsAdapter.setmOnItemClickListener(this);
    }

    private void initView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.all_news_recycler_view);
        mBackBtn = (ImageView) findViewById(R.id.jump_all_news_activity_back_home_activity);
    }

    private void initTouchEvent(){
        mBackBtn.setOnClickListener(this);
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
        Intent intent = new Intent(AllNewsActivity.this, NewsDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jump_all_news_activity_back_home_activity:
                onBackPressed();
                break;
        }
    }
}
