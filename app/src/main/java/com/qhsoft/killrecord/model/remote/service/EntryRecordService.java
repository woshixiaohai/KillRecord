package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.EntryRecoderDetailBean;
import com.qhsoft.killrecord.model.remote.bean.EntryRecoderListBean;
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


public interface EntryRecordService {
    /**
     * 进场记录
     *
     */
    @GET("tBruteApprRecController.do?datagrid")
    Observable<EntryRecoderListBean> getEntryRecoderList(@Query("sessionId") String sessionId,
                                                         @Query("billno") String billno,
                                                         @Query("supplyname") String supplyname,
                                                         @Query("page") int page,
                                                         @Query("rows") int rows

    );


    @POST("tBruteApprRecController.do?doAddApp")
    Observable<BaseBean> doAddApp(@Query("sessionId") String sessionId,
                                  @Body RequestBody requestBody

                                  );
    @POST("tBruteApprRecController.do?doUpdateApp")
    Observable<BaseBean> doUpdateApp(@Query("sessionId") String sessionId,
                                  @Body RequestBody requestBody

    );

    @GET("tBruteApprRecController.do?doDel")
    Observable<BaseBean> doDel(@Query("sessionId") String sessionId,
                               @Query("id") String id

    );

    @POST("tBruteApprRecController.do?getEntityInfo")
    Observable<EntryRecoderDetailBean> getDetail(@Query("sessionId") String sessionId,
                                                 @Query("id") String id

    );

    @GET("tBruteApprRecController.do?checkDel")
    Observable<BaseAddBean> checkDel(@Query("sessionId") String sessionId,
                                     @Query("billno") String billno,
                                     @Query("circleNo") String circleNo

    );









}
