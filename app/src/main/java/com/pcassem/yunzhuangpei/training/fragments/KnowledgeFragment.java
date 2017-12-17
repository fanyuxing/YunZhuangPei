package com.pcassem.yunzhuangpei.training.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.home.presenter.SearchKnowledgePresenter;
import com.pcassem.yunzhuangpei.training.activities.KnowledgeDetailsActivity;
import com.pcassem.yunzhuangpei.training.activities.KnowledgeDetailsDocActivity;
import com.pcassem.yunzhuangpei.training.adapter.KnowledgeItemAdapter;
import com.pcassem.yunzhuangpei.training.presenter.KnowledgePresenter;
import com.pcassem.yunzhuangpei.training.view.KnowledgeView;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.List;


public class KnowledgeFragment extends Fragment implements KnowledgeView, KnowledgeItemAdapter.OnItemClickListener {

    public static final String TAG = "KnowledgeFragment";

    private RecyclerView mRecyclerView;
    private KnowledgeItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<KnowledgeEntity> mTrainingData;
    private KnowledgePresenter trainingPresenter;
    private SearchKnowledgePresenter searchKnowledgePresenter;

    public static KnowledgeFragment newInstance() {

        KnowledgeFragment knowledgeFragment = new KnowledgeFragment();
        return knowledgeFragment;
    }

    private int module;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge_item,container,false);
        initView(view);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    public void updateKnowledge(String repository, String category, String firstLevel, String secondLevel, String thirdLevel){

        if (trainingPresenter == null){
            trainingPresenter = new KnowledgePresenter(this);
            trainingPresenter.onCreate();
        }
        trainingPresenter.getKnowledgeList(repository, category, firstLevel, secondLevel, thirdLevel);
        Log.d(TAG, "updateKnowledge: " + repository + category + firstLevel + secondLevel + thirdLevel);
        module = 2;
    }

    public void updateSearchKnowledge(String repository, String keyword){
        if (searchKnowledgePresenter == null){
            searchKnowledgePresenter = new SearchKnowledgePresenter(this);
            searchKnowledgePresenter.onCreate();
        }
        searchKnowledgePresenter.getSearchKnowledgeList(repository, keyword);
        Log.d(TAG, "updateSearchKnowledge: " + repository + keyword);
        module = 3;
    }

    private void initView(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
    }

    @Override
    public void onItemClick(View view, String position) {
        char isFile = position.charAt(0);
        int pos = Integer.parseInt(position.substring(1,position.length()));
        if (isFile == 'Y'){
            Intent intent = new Intent(getActivity(), KnowledgeDetailsDocActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("knowledgeID", pos);
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            Intent intent = new Intent(getActivity(), KnowledgeDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("knowledgeID", pos);
            bundle.putInt("module",module);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void onSuccess(ResultListEntity<KnowledgeEntity> newsListEntity) {
        mTrainingData = newsListEntity.getResult();
        if (mTrainingData == null){
            Toast.makeText(getContext(), "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mAdapter == null){
            mAdapter = new KnowledgeItemAdapter(mTrainingData);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setmOnItemClickListener(this);
        }else {
            mAdapter.setmData(mTrainingData);
        }
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "网络出错", Toast.LENGTH_SHORT).show();
    }
}