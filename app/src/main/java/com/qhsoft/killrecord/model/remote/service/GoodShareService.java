package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.ShareGoodDetailBean;
import com.qhsoft.killrecord.model.remote.bean.ShareGoodListBean;
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


public interface GoodShareService {

    @POST("tBiPubIcItemController.do?datagrid")
    Observable<ShareGoodListBean> getList(@Query("sessionId") String sessionId,
                                          @Query("name") String name,
                                          @Query("barCode") String barCode,
                                          @Query("page") int page,
                                          @Query("rows") int rows

    );

    @POST("tBiPubIcItemController.do?doAddApp")
    Observable<BaseBean> doAddApp(@Query("sessionId") String sessionId,
                                  @Body RequestBody requestBody


    );



    @GET("tBiPubIcItemController.do?getItemInfo")
    Observable<ShareGoodDetailBean> getCheck(@Query("sessionId") String sessionId,
                                             @Query("id") String id);


    @POST("tBiPubIcItemController.do?doUpdateApp")
    Observable<BaseBean> doUpdateApp(@Query("sessionId") String sessionId,
                                     @Body RequestBody requestBody


    );
    @GET("tBiPubIcItemController.do?doDel")
    Observable<BaseBean> doDel(@Query("sessionId") String sessionId,
                               @Query("id") String id);




}
