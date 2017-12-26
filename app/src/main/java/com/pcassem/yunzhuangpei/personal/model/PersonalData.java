package com.pcassem.yunzhuangpei.personal.model;

import com.pcassem.yunzhuangpei.entity.LoginBean;
import com.pcassem.yunzhuangpei.entity.RegisterBean;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.UserEntity;
import com.pcassem.yunzhuangpei.http.PersonalAPI;
import com.pcassem.yunzhuangpei.http.RetrofitHelper;

import rx.Observable;

public class PersonalData {
    private PersonalAPI mPersonalAPI;

    public PersonalData() {
        this.mPersonalAPI = RetrofitHelper.getInstance().getPersonalAPI();
    }

    //登录
    public Observable<ResultListEntity<UserEntity>> login(LoginBean user) {
        return mPersonalAPI.login(user);
    }

    //注册
    public Observable<ResultListEntity<String>> userRegister(RegisterBean user) {
        return mPersonalAPI.userRegister(user);
    }
}
