package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.OutrecordDetailBean;
import com.qhsoft.killrecord.model.remote.bean.OutrecordListBean;
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


public interface OutrecordService {

    @POST("tBruteEnterRecController.do?datagrid")
    Observable<OutrecordListBean> getOutrecordList(@Query("sessionId") String sessionId,
                                                       @Query("billno") String billno,
                                                       @Query("purchaser") String purchaser,
                                                       @Query("page") int page,
                                                       @Query("rows") int rows

    );
    @POST("tBruteEnterRecController.do?doAddApp")
    Observable<BaseBean> doAddApp(@Query("sessionId") String sessionId,
                                  @Body RequestBody requestBody

    );
    @POST("tBruteEnterRecController.do?doUpdateApp")
    Observable<BaseBean> doUpdateApp(@Query("sessionId") String sessionId,
                                     @Body RequestBody requestBody

    );

    @GET("tBruteEnterRecController.do?doDel")
    Observable<BaseBean> doDel(@Query("sessionId") String sessionId,
                               @Query("id") String id

    );

    @POST("tBruteEnterRecController.do?getEntityInfo")
    Observable<OutrecordDetailBean> getDetail(@Query("sessionId") String sessionId,
                                              @Query("id") String id

    );











}
