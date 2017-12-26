package com.pcassem.yunzhuangpei.personal.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.entity.RegisterBean;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.activities.LoginActivity;
import com.pcassem.yunzhuangpei.personal.presenter.LoginPresenter;
import com.pcassem.yunzhuangpei.personal.view.RegisterView;


public class RegisterFragment extends Fragment implements RegisterView, View.OnClickListener {

    public static final String TAG = "RegisterFragment";

    private EditText mPhoneNumber;
    private EditText mValidationNumber;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private EditText mName;
    private Button mRegister;

    private LoginPresenter mLoginPresenter;

    private FragmentManager manager;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initView(view);
        initTouchEvent();
        manager = getFragmentManager();

        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.onCreate();
        return view;
    }

    private void initView(View v){
        mPhoneNumber = (EditText) v.findViewById(R.id.phone_number);
        mValidationNumber = (EditText) v.findViewById(R.id.validation_number);
        mPassword = (EditText)v.findViewById(R.id.password);
        mConfirmPassword = (EditText)v.findViewById(R.id.confirm_password);
        mName = (EditText)v.findViewById(R.id.name);
        mRegister = (Button) v.findViewById(R.id.register);
    }

    private void initTouchEvent(){
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.register:
                 register();
                 break;
         }
    }

    private void register(){
        RegisterBean user = new RegisterBean();
        String phoneNumber = mPhoneNumber.getText().toString();
        String validationNumber = mValidationNumber.getText().toString();
        String password = mPassword.getText().toString();
        String confirmPassword = mConfirmPassword.getText().toString();
        String name = mName.getText().toString();

        if (TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(validationNumber)){
            Toast.makeText(getActivity(), "验证码不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(getActivity(), "确认密码错误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name)){
            Toast.makeText(getActivity(), "昵称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        user.setPhoneNumber(phoneNumber);
        user.setValidationNumber(validationNumber);
        user.setPassword(password);
        user.setName(name);
        mLoginPresenter.userRegister(user);
    }

    @Override
    public void onSuccess(ResultListEntity<String> registerStatus) {
        if (registerStatus.getCode() == 0){
            ((LoginActivity)getActivity()).findViewById(R.id.constact_group).setEnabled(true);
            ((LoginActivity)getActivity()).findViewById(R.id.constact_all).setEnabled(false);
            LoginFragment myJDEditFragment = new LoginFragment();
            ft = manager.beginTransaction();
            ft.replace(R.id.id_content, myJDEditFragment);
            ft.commit();
        }
        if (registerStatus.getCode() == 1){
            Toast.makeText(getActivity(), "验证码错误", Toast.LENGTH_SHORT).show();
        }
        
        if (registerStatus.getCode() == 2 || registerStatus.getCode() == 3){
            Toast.makeText(getActivity(), "手机号码已经存在", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "注册失败，请重新注册", Toast.LENGTH_SHORT).show();
    }
}
