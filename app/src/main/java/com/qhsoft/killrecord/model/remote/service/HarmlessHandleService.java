package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.HarmlessHandlerDetailBean;
import com.qhsoft.killrecord.model.remote.bean.HarmlessHandlerListBean;
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


public interface HarmlessHandleService {

    @POST("tBruteHarmHandController.do?datagrid")
    Observable<HarmlessHandlerListBean> getList(@Query("sessionId") String sessionId,
                                                @Query("billno") String billno,
                                                @Query("functionary") String functionary,
                                                @Query("page") int page,
                                                @Query("rows") int rows

    );


    @POST("tBruteHarmHandController.do?doAddApp")
    Observable<BaseBean> doAddApp(@Query("sessionId") String sessionId,
                                  @Body RequestBody requestBody

    );
    @POST("tBruteHarmHandController.do?doUpdateApp")
    Observable<BaseBean> doUpdateApp(@Query("sessionId") String sessionId,
                                     @Body RequestBody requestBody

    );

    @GET("tBruteHarmHandController.do?doDel")
    Observable<BaseBean> doDel(@Query("sessionId") String sessionId,
                               @Query("id") String id

    );

    @POST("tBruteHarmHandController.do?getEntityInfo")
    Observable<HarmlessHandlerDetailBean> getDetail(@Query("sessionId") String sessionId,
                                                    @Query("id") String id

    );



}
