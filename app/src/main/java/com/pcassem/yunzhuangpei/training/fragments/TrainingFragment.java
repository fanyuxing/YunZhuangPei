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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.adapter.ExportListAdapter;
import com.pcassem.yunzhuangpei.home.activities.NewsDetailsActivity;
import com.pcassem.yunzhuangpei.training.activities.ProblemSolvingActivity;
import com.pcassem.yunzhuangpei.training.activities.ProcessActivity;
import com.pcassem.yunzhuangpei.training.activities.ProjectCasesActivity;
import com.pcassem.yunzhuangpei.training.activities.StandardActivity;
import com.pcassem.yunzhuangpei.training.activities.TrainingCoursesActivity;
import com.pcassem.yunzhuangpei.training.adapter.TrainingListAdapter;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.util.ArrayList;

public class TrainingFragment extends Fragment implements View.OnClickListener, TrainingListAdapter.OnItemClickListener {

    private LinearLayout mJumpProcessActivity;
    private LinearLayout mJumpStandardActivity;
    private LinearLayout mJumpProblemSolvingActivity;
    private LinearLayout mJumpTrainingCoursesActivity;
    private TextView mJumpMoreProjectCases;

    private RecyclerView mRecyclerView;
    private TrainingListAdapter mTrainingListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public static TrainingFragment newInstance() {
        TrainingFragment trainingFragment = new TrainingFragment();
        return trainingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        initView(view);
        initTouchEvent();

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mTrainingListAdapter = new TrainingListAdapter(getData());

        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置adapter
        mRecyclerView.setAdapter(mTrainingListAdapter);
        mTrainingListAdapter.setmOnItemClickListener(this);

        return view;
    }

    private void initView(View view){

        mJumpProcessActivity = (LinearLayout) view.findViewById(R.id.training_process);
        mJumpStandardActivity = (LinearLayout)view.findViewById(R.id.training_standard);
        mJumpProblemSolvingActivity = (LinearLayout)view.findViewById(R.id.training_problem_solving);
        mJumpTrainingCoursesActivity = (LinearLayout)view.findViewById(R.id.training_training_courses);
        mJumpMoreProjectCases = (TextView) view.findViewById(R.id.training_project_cases_more);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.training_recycler_view);
    }

    private void initTouchEvent(){
        mJumpProcessActivity.setOnClickListener(this);
        mJumpStandardActivity.setOnClickListener(this);
        mJumpProblemSolvingActivity.setOnClickListener(this);
        mJumpTrainingCoursesActivity.setOnClickListener(this);

        mJumpMoreProjectCases.setOnClickListener(this);
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
        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.training_process:
                Intent processIntent = new Intent(getActivity(), ProcessActivity.class);
                startActivity(processIntent);
                break;
            case R.id.training_standard:
                Intent standardIntent = new Intent(getActivity(), StandardActivity.class);
                startActivity(standardIntent);
                break;
            case R.id.training_problem_solving:
                Intent problemSolvingIntent = new Intent(getActivity(), ProblemSolvingActivity.class);
                startActivity(problemSolvingIntent);
                break;
            case R.id.training_training_courses:
                Intent coursesIntent = new Intent(getActivity(), TrainingCoursesActivity.class);
                startActivity(coursesIntent);
                break;
            case R.id.training_project_cases_more:
                Intent projectCasesMoreIntent = new Intent(getActivity(),ProjectCasesActivity.class);
                startActivity(projectCasesMoreIntent);
                break;
        }
    }
}
