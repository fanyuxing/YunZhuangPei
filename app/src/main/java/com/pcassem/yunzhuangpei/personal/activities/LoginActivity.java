package com.pcassem.yunzhuangpei.personal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.personal.fragments.LoginFragment;
import com.pcassem.yunzhuangpei.personal.fragments.RegisterFragment;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button title_left_btn , title_right_btn;
    private ImageView backIv;


    /**
     * Fragment管理器
     */
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private RegisterFragment mLFragment ;
    private LoginFragment mRFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initTouchEvent();
    }


    private void initView() {

        backIv = (ImageView) findViewById(R.id.back_iv);

        title_left_btn = (Button)findViewById(R.id.constact_group);
        title_right_btn = (Button)findViewById(R.id.constact_all);

        title_left_btn.setOnClickListener(this);
        title_left_btn.performClick();

        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

        mLFragment = new RegisterFragment();
        mTransaction.replace(R.id.id_content, mLFragment);
        mTransaction.commit();

    }

    private void initTouchEvent(){
        title_right_btn.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.constact_group:
                if(title_left_btn.isEnabled()){
                    title_left_btn.setEnabled(false);
                    title_right_btn.setEnabled(true);
                }
                mFragmentManager = getSupportFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                if(mLFragment == null){
                    mLFragment = new RegisterFragment();

                }
                mTransaction.replace(R.id.id_content, mLFragment);
                mTransaction.commit();
                break;

            case R.id.constact_all:
                if(title_right_btn.isEnabled()){
                    title_left_btn.setEnabled(true);
                    title_right_btn.setEnabled(false);
                }
                mFragmentManager = getSupportFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                if(mRFragment == null){
                    mRFragment = new LoginFragment();
                }
                mTransaction.replace(R.id.id_content, mRFragment);
                mTransaction.commit();
                break;
        }
    }

}

