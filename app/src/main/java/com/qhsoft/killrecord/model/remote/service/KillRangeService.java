package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.KillRangeDetailBean;
import com.qhsoft.killrecord.model.remote.bean.KillRangeListBean;
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


public interface KillRangeService {

    @POST("tBiShareBruteController.do?datagrid")
    Observable<KillRangeListBean> getList(@Query("sessionId") String sessionId,
                                          @Query("bruteName") String bruteName,
                                          @Query("billno") String billno,
                                          @Query("page") int page,
                                          @Query("rows") int rows

    );
    @POST("tBiShareBruteController.do?doAddApp")
    Observable<BaseBean> doAddApp(@Query("sessionId") String sessionId,
                                          @Body RequestBody requestBody


    );
    @POST("tBiShareBruteController.do?doUpdateApp")
    Observable<BaseBean> doUpdateApp(@Query("sessionId") String sessionId,
                                  @Body RequestBody requestBody


    );

    @GET("tBiShareBruteController.do?getEntityInfo")
    Observable<KillRangeDetailBean> getDetail(@Query("sessionId") String sessionId,
                                              @Query("id") String id


    );
    @POST("tBiShareBruteController.do?doDel")
    Observable<BaseBean> doBatchDel(@Query("sessionId") String sessionId,
                                    @Query("id") String id


    );


}
