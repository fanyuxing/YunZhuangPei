package com.pcassem.yunzhuangpei.http;

import com.pcassem.yunzhuangpei.entity.ExportDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhangqi on 2017/12/21.
 */

public interface AdvisoryAPI {

    //推荐专家列表
    @GET("/app/specialist/commendList")
    Observable<ResultListEntity<ExportEntity>> getCommendExportList();

    //全部专家列表
    @GET("/app/specialist/list")
    Observable<ResultListEntity<ExportEntity>> getAllExportList();

    //专家详情
    @GET("/app/specialist/info")
    Observable<ResultListEntity<ExportDetailsEntity>> getExportDetails(@Query("specialistID") int specialistID);

}
