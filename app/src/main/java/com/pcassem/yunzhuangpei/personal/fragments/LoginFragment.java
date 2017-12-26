package com.pcassem.yunzhuangpei.personal.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.pcassem.yunzhuangpei.entity.LoginBean;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.UserEntity;
import com.pcassem.yunzhuangpei.home.activities.HomeActivity;
import com.pcassem.yunzhuangpei.personal.activities.ForgetPasswordActivity;
import com.pcassem.yunzhuangpei.personal.presenter.LoginPresenter;
import com.pcassem.yunzhuangpei.personal.view.LoginView;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment implements LoginView, View.OnClickListener {

    public static final String TAG = "Loginfragment";

    private EditText mPhoneNumber;
    private EditText mPassword;
    private TextView mJumpForgetPassword;
    private Button mLogin;

    private LoginPresenter mLoginPresenter;
    private UserEntity mUser;
    private String token1;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initView(view);
        initTouchEvent();
        mSharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.onCreate();
        return view;
    }

    private void initView(View view){
        mPhoneNumber = (EditText) view.findViewById(R.id.phone_number);
        mPassword = (EditText) view.findViewById(R.id.password);
        mJumpForgetPassword = (TextView) view.findViewById(R.id.forget_password);
        mLogin = (Button) view.findViewById(R.id.login);
    }

    private void initTouchEvent(){
        mJumpForgetPassword.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_password:
                Intent forgetPasswordIntent = new Intent(getActivity(), ForgetPasswordActivity.class);
                startActivity(forgetPasswordIntent);
                break;
            case R.id.login:
                login();
                break;
        }
    }

    private void login(){
        LoginBean user = new LoginBean();
        String phoneNumber = mPhoneNumber.getText().toString();
        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        mLoginPresenter.login(user);
    }

    private void connectRongServer(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                RongIM.getInstance().setMessageAttachedUserInfo(true);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, "connect failure errorCode is :" + errorCode.getValue());
            }

            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "token is error , please check token and appkey ");
            }
        });
    }

    @Override
    public void onSuccess(ResultListEntity<UserEntity> userInfo) {
        if (userInfo.getCode() == 1){
            Toast.makeText(getActivity(), "账号或密码错误", Toast.LENGTH_SHORT).show();
        }else {
            mUser = userInfo.getResult().get(0);
            token1 = mUser.getTokenRongCloud();
            connectRongServer(token1);
            mEditor.putString("username", mUser.getName().toString());
            mEditor.commit();
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "登录失败，请重新登录", Toast.LENGTH_SHORT).show();
    }
}
