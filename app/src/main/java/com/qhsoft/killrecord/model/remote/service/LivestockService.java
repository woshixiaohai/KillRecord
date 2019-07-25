package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.LivestockDetailBean;
import com.qhsoft.killrecord.model.remote.bean.LivestockListBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description:
 * Author:lin
 * Date:2017-09-29
 */


public interface LivestockService {

    @POST("tBruteCheckInsController.do?datagridStopButcher")
    Observable<LivestockListBean> getList(@Query("sessionId") String sessionId,
                                          @Query("page") int page,
                                          @Query("rows") int rows

    );

    @POST("tBruteCheckInsController.do?getUpdateButcher")
    Observable<LivestockDetailBean> getDetail(@Query("sessionId") String sessionId,
                                              @Query("id") String id


    );


}
