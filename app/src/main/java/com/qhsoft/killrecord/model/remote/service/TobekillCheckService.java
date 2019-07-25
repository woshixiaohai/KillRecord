package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.TobekillCheckDetailBean;
import com.qhsoft.killrecord.model.remote.bean.TobekillCheckListBean;
import com.qhsoft.killrecord.net.BaseAddBean;
import com.qhsoft.killrecord.net.BaseBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description:
 * Author:lin
 * Date:2017-09-29
 */


public interface TobekillCheckService {
    /**
     * 待宰检验
     *
     */
    @POST("tBruteCheckInsController.do?datagrid&type=0")
    Observable<TobekillCheckListBean> getTobeKillCheckList(@Query("sessionId") String sessionId,
                                                          @Query("billno") String billno,
                                                          @Query("supplyname") String supplyname,
                                                          @Query("page") int page,
                                                          @Query("rows") int rows

    );



    @POST("tBruteCheckInsController.do?doAddApp")
    Observable<BaseAddBean> doAddApp(@Query("sessionId") String sessionId,
                                     @Body RequestBody requestBody

    );
    @POST("tBruteCheckInsController.do?doUpdateApp")
    Observable<BaseBean> doUpdateApp(@Query("sessionId") String sessionId,
                                     @Body RequestBody requestBody

    );

    @GET("tBruteCheckInsController.do?doDel")
    Observable<BaseBean> doDel(@Query("sessionId") String sessionId,
                               @Query("id") String id

    );

    @POST("tBruteCheckInsController.do?getEntityInfo")
    Observable<TobekillCheckDetailBean> getDetail(@Query("sessionId") String sessionId,
                                                  @Query("id") String id

    );

    @GET("tBruteCheckInsController.do?checkDel")
    Observable<BaseAddBean> checkDel(@Query("sessionId") String sessionId,
                                     @Query("billno") String billno,
                                     @Query("circleNo") String circleNo

    );









}
