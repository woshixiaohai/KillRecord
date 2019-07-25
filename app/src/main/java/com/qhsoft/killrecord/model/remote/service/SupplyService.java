package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.SupplyListBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description:
 * Author:lin
 * Date:2017-09-29
 */


public interface SupplyService {

    @POST("tOperatorBiController.do?datagrid&fmainBusiness=供货商")
    Observable<SupplyListBean> getList(@Query("sessionId") String sessionId,
                                       @Query("fmanageName") String fmanageName,
                                       @Query("page") int page,
                                       @Query("rows") int rows

    );


}
