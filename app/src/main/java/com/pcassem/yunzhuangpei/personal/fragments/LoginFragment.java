package com.pcassem.yunzhuangpei.personal.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.personal.activities.ForgetPasswordActivity;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private TextView mJumpForgetPassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initView(view);
        initTouchEvent();

        return view;
    }

    private void initView(View view){
        mJumpForgetPassword = (TextView) view.findViewById(R.id.forget_password);
    }

    private void initTouchEvent(){
        mJumpForgetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_password:
                Intent forgetPasswordIntent = new Intent(getActivity(), ForgetPasswordActivity.class);
                startActivity(forgetPasswordIntent);
                break;
        }
    }
}
