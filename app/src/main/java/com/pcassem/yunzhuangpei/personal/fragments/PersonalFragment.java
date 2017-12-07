package com.pcassem.yunzhuangpei.personal.fragments;


import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.personal.activities.HelpActivity;
import com.pcassem.yunzhuangpei.personal.activities.LoginActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyAdvisoryActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyCollectionActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyDownloadActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyProjectActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyTrainingActivity;
import com.pcassem.yunzhuangpei.personal.activities.PersonalInformationActivity;
import com.pcassem.yunzhuangpei.personal.activities.SystemSettingsActivity;
import com.pcassem.yunzhuangpei.view.ItemPersonalList;

public class PersonalFragment extends Fragment implements View.OnClickListener {

    private TextView mJumpLoginActivity;
    private TextView mJumpPersonalInfomationActivity;
    private ItemPersonalList mJumpMyProjectActivity;
    private ItemPersonalList mJumpMydownloadActivity;
    private ItemPersonalList mJumpMyadvisoryActivity;
    private ItemPersonalList mJumpMyCollectionActivity;
    private ItemPersonalList mJumpMyTrainingActivity;
    private ItemPersonalList mJumpHelpInfoActivity;
    private ItemPersonalList mJumpSystemSettingsActivity;


    public static PersonalFragment newInstance() {
        PersonalFragment personalFragment = new PersonalFragment();
        return personalFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        initView(view);
        initTouchEvent();

        return view;
    }

    private void initView(View view) {
        mJumpLoginActivity = (TextView) view.findViewById(R.id.personal_jump_login_activity);
        mJumpPersonalInfomationActivity = (TextView) view.findViewById(R.id.personal_jump_personal_infomation_activity);
        mJumpMyProjectActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_project_activity);
        mJumpMydownloadActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_download_activity);
        mJumpMyadvisoryActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_advisory_activity);
        mJumpMyCollectionActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_collection_activity);
        mJumpMyTrainingActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_training_activity);
        mJumpHelpInfoActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_help_info_activity);
        mJumpSystemSettingsActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_system_settings);
    }

    private void initTouchEvent() {
        mJumpLoginActivity.setOnClickListener(this);
        mJumpPersonalInfomationActivity.setOnClickListener(this);
        mJumpMyProjectActivity.setOnClickListener(this);
        mJumpMydownloadActivity.setOnClickListener(this);
        mJumpMyadvisoryActivity.setOnClickListener(this);
        mJumpMyCollectionActivity.setOnClickListener(this);
        mJumpMyTrainingActivity.setOnClickListener(this);
        mJumpHelpInfoActivity.setOnClickListener(this);
        mJumpSystemSettingsActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_jump_login_activity:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.personal_jump_personal_infomation_activity:
                Intent intent1 = new Intent(getActivity(), PersonalInformationActivity.class);
                startActivity(intent1);
                break;
            case R.id.personal_jump_my_project_activity:
                Intent intent2 = new Intent(getActivity(), MyProjectActivity.class);
                startActivity(intent2);
                break;
            case R.id.personal_jump_my_download_activity:
                Intent intent3 = new Intent(getActivity(), MyDownloadActivity.class);
                startActivity(intent3);
                break;
            case R.id.personal_jump_my_advisory_activity:
                Intent intent4 = new Intent(getActivity(), MyAdvisoryActivity.class);
                startActivity(intent4);
                break;
            case R.id.personal_jump_my_collection_activity:
                Intent intent5 = new Intent(getActivity(), MyCollectionActivity.class);
                startActivity(intent5);
                break;
            case R.id.personal_jump_my_training_activity:
                Intent intent6 = new Intent(getActivity(), MyTrainingActivity.class);
                startActivity(intent6);
                break;
            case R.id.personal_jump_help_info_activity:
                Intent intent7 = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent7);
                break;
            case R.id.personal_jump_system_settings:
                Intent intent8 = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intent8);
                break;
        }
    }
}
