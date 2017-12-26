package com.pcassem.yunzhuangpei.advisory.model;

import com.pcassem.yunzhuangpei.advisory.presenter.ExportPresenter;
import com.pcassem.yunzhuangpei.entity.ExportDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.http.AdvisoryAPI;
import com.pcassem.yunzhuangpei.http.RetrofitHelper;

import rx.Observable;

/**
 * Created by zhangqi on 2017/12/21.
 */

public class ExportData {
    private AdvisoryAPI mAdvisoryAPI;

    public ExportData() {
        this.mAdvisoryAPI = RetrofitHelper.getInstance().getAdvisoryAPI();
    }

    //推荐专家列表
    public Observable<ResultListEntity<ExportEntity>> getCommendExportList() {
        return mAdvisoryAPI.getCommendExportList();
    }

   //全部专家列表
    public Observable<ResultListEntity<ExportEntity>> getAllExportList(){
        return mAdvisoryAPI.getAllExportList();
    }

    //专家详情
    public Observable<ResultListEntity<ExportDetailsEntity>> getExportDetails(int specialistID){
        return mAdvisoryAPI.getExportDetails(specialistID);
    }

}
