package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.TraceProductDetailBean;
import com.qhsoft.killrecord.model.remote.bean.TraceProductListBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description:
 * Author:lin
 * Date:2017-09-29
 */


public interface TraceProductService {

    @POST("tBruteEnterRecController.do?datagridProduct")
    Observable<TraceProductListBean> getList(@Query("sessionId") String sessionId,
                                             @Query("page") int page,
                                             @Query("rows") int rows

    );

    @POST("tBruteEnterRecController.do?getUpdateProduct")
    Observable<TraceProductDetailBean> getDetail(@Query("sessionId") String sessionId,
                                                 @Query("id") String id,
                                                 @Query("zid") String zid


    );


}
