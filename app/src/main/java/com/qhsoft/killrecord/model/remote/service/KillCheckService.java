package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.KillCheckListBean;
import com.qhsoft.killrecord.model.remote.bean.KillCheckOneDetailBean;
import com.qhsoft.killrecord.model.remote.bean.KillCheckTwoDetailBean;
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


public interface KillCheckService {

    @GET("tBruteCheckInsController.do?datagridView")
    Observable<KillCheckListBean> getKillCheckList(@Query("sessionId") String sessionId,
                                                   @Query("billno") String billno,
                                                   @Query("purchaser") String purchaser,
                                                   @Query("isSuccess") String isSuccess,
                                                   @Query("page") int page,
                                                   @Query("rows") int rows

    );

    @GET("tBruteCheckInsController.do?doDel")
    Observable<BaseBean> doDel(@Query("sessionId") String sessionId,
                               @Query("id") String id

    );

    @POST("tBruteCheckInsController.do?doAddApp")
    Observable<KillCheckOneDetailBean> doAddAppOne(@Query("sessionId") String sessionId,
                                  @Body RequestBody requestBody

    );
    @POST("tBruteRuleAftController.do?doAddApp")
    Observable<BaseBean> doAddAppTwo(@Query("sessionId") String sessionId,
                                     @Body RequestBody requestBody

    );
    @POST("tBruteCheckInsController.do?doUpdateApp")
    Observable<BaseBean> doUpdateAppOne(@Query("sessionId") String sessionId,
                                     @Body RequestBody requestBody

    );
    @POST("tBruteRuleAftController.do?doUpadateApp")
    Observable<BaseBean> doUpdateAppTwo(@Query("sessionId") String sessionId,
                                        @Body RequestBody requestBody

    );



    @GET("tBruteCheckInsController.do?getEntityInfo")
    Observable<KillCheckOneDetailBean> getEntityInfoOne(@Query("sessionId") String sessionId,
                                                        @Query("id") String id

    );

    @GET("tBruteRuleAftController.do?getEntityInfo")
    Observable<KillCheckTwoDetailBean> getEntityInfoTwo(@Query("sessionId") String sessionId,
                                                        @Query("id") String id

    );








}
