package com.pcassem.yunzhuangpei.home.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.fragments.AdvisoryFragment;
import com.pcassem.yunzhuangpei.forum.fragments.ForumFragment;
import com.pcassem.yunzhuangpei.home.fragments.HomeFragment;
import com.pcassem.yunzhuangpei.personal.fragments.PersonalFragment;
import com.pcassem.yunzhuangpei.training.fragments.TrainingFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTVAdvisory, mTVTraining, mTVForum, mTVPersonal;
    private ImageView mIVHome;
    private AdvisoryFragment mAdvisoryFragment;
    private TrainingFragment mTrainingFragment;
    private HomeFragment mHomeFragment;
    private ForumFragment mForumFragment;
    private PersonalFragment mPersonalFragment;

    private Fragment currentFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        mTVAdvisory.setOnClickListener(this);
        mTVTraining.setOnClickListener(this);
        mIVHome.setOnClickListener(this);
        mTVForum.setOnClickListener(this);
        mTVPersonal.setOnClickListener(this);

        setDefaultFragment();
    }

    private void initView() {
        mTVAdvisory = (TextView) findViewById(R.id.home_advisory);
        mTVTraining = (TextView) findViewById(R.id.home_training);
        mIVHome = (ImageView) findViewById(R.id.home_home);
        mTVForum = (TextView) findViewById(R.id.home_forum);
        mTVPersonal = (TextView) findViewById(R.id.home_personal);
    }

    private void setDefaultFragment() {
        switchFragment(2);
        setTabHomeState(mIVHome, R.drawable.btm_nav_home_selected);
    }

    @Override
    public void onClick(View v) {
        resetTabState();
        switch (v.getId()) {
            case R.id.home_advisory:
                setTabState(mTVAdvisory, R.drawable.btm_nav_advisory_selected, getTextColor(R.color.color_13386d));
                switchFragment(0);
                break;
            case R.id.home_training:
                setTabState(mTVTraining, R.drawable.btm_nav_training_selected, getTextColor(R.color.color_13386d));
                switchFragment(1);
                break;
            case R.id.home_home:
                setTabHomeState(mIVHome, R.drawable.btm_nav_home_selected);
                switchFragment(2);
                break;
            case R.id.home_forum:
                setTabState(mTVForum, R.drawable.btm_nav_forum_selected, getTextColor(R.color.color_13386d));
                switchFragment(3);
                break;
            case R.id.home_personal:
                setTabState(mTVPersonal, R.drawable.btm_nav_personal_selected, getTextColor(R.color.color_13386d));
                switchFragment(4);
                break;
        }
    }

    private void switchFragment(int i) {
        switch (i) {
            case 0:
                if (mAdvisoryFragment == null)
                    mAdvisoryFragment = mAdvisoryFragment.newInstance();

                showFragment(mAdvisoryFragment);
                break;
            case 1:
                if (mTrainingFragment == null)
                    mTrainingFragment = mTrainingFragment.newInstance();

                showFragment(mTrainingFragment);
                break;
            case 2:
                if (mHomeFragment == null)
                    mHomeFragment = mHomeFragment.newInstance();

                showFragment(mHomeFragment);
                break;
            case 3:
                if (mForumFragment == null)
                    mForumFragment = mForumFragment.newInstance();

                showFragment(mForumFragment);
                break;
            case 4:
                if (mPersonalFragment == null)
                    mPersonalFragment = mPersonalFragment.newInstance();

                showFragment(mPersonalFragment);
                break;
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (currentFragment != fragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.home_content, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }

    private void resetTabState() {
        setTabState(mTVAdvisory, R.drawable.btm_nav_advisory, getTextColor(R.color.color_999999));
        setTabState(mTVTraining, R.drawable.btm_nav_training, getTextColor(R.color.color_999999));
        setTabHomeState(mIVHome, R.drawable.btm_nav_home);
        setTabState(mTVForum, R.drawable.btm_nav_forum, getTextColor(R.color.color_999999));
        setTabState(mTVPersonal, R.drawable.btm_nav_personal, getTextColor(R.color.color_999999));
    }

    private void setTabState(TextView textView, int image, int color) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, image, 0, 0);//Call requires API level 17
        textView.setTextColor(color);
    }

    private void setTabHomeState(ImageView imageView, int image) {
        imageView.setImageResource(image);
    }


    private int getTextColor(int i) {
        return ContextCompat.getColor(this, i);
    }
}
