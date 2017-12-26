package com.pcassem.yunzhuangpei.http;

import com.pcassem.yunzhuangpei.entity.LoginBean;
import com.pcassem.yunzhuangpei.entity.RegisterBean;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.UserEntity;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zhangqi on 2017/12/21.
 */

public interface PersonalAPI {

    //登录
    @Headers({"Content-type:application/json", "Content-Length:60"})
    @POST("/app/user/login")
    Observable<ResultListEntity<UserEntity>> login(@Body LoginBean user);

    //注册
    @Headers({"Content-type:application/json", "Content-Length:60"})
    @POST("/app/user/register")
    Observable<ResultListEntity<String>> userRegister(@Body RegisterBean user);
}
