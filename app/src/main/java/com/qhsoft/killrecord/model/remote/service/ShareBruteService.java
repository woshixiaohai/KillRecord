package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.ShareBruteDetailBean;
import com.qhsoft.killrecord.model.remote.bean.ShareBruteListBean;
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


public interface ShareBruteService {

    @POST("tShareBruteController.do?datagrid")
    Observable<ShareBruteListBean> getList(@Query("sessionId") String sessionId,
                                           @Query("bruteName") String bruteName,
                                           @Query("billno") String billno,
                                           @Query("page") int page,
                                           @Query("rows") int rows

    );
    @POST("tShareBruteController.do?doAddApp")
    Observable<BaseBean> doAddApp(@Query("sessionId") String sessionId,
                                  @Body RequestBody requestBody


    );
    @POST("tShareBruteController.do?doUpdateApp")
    Observable<BaseBean> doUpdateApp(@Query("sessionId") String sessionId,
                                     @Body RequestBody requestBody


    );

    @GET("tShareBruteController.do?getEntityInfo")
    Observable<ShareBruteDetailBean> getDetail(@Query("sessionId") String sessionId,
                                               @Query("id") String id


    );
    @POST("tShareBruteController.do?doDel")
    Observable<BaseBean> doBatchDel(@Query("sessionId") String sessionId,
                                    @Query("id") String id


    );


}
