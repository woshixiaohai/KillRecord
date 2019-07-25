package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.GoodDetailBean;
import com.qhsoft.killrecord.model.remote.bean.GoodListBean;
import com.qhsoft.killrecord.model.remote.bean.IcItemInfoBean;
import com.qhsoft.killrecord.model.remote.bean.MenuItemBean;
import com.qhsoft.killrecord.net.BaseBean;

import java.util.List;

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


public interface GoodService {

    @POST("tBiIcitemController.do?datagridSon")
    Observable<GoodListBean> getList(@Query("sessionId") String sessionId,
                                     @Query("name") String name,
                                     @Query("barCode") String barCode,
                                     @Query("page") int page,
                                     @Query("rows") int rows

    );
    @POST("tBiIcitemController.do?doAddApp")
    Observable<BaseBean> doAddApp(@Query("sessionId") String sessionId,
                                  @Body RequestBody requestBody


    );
    @POST("tBiIcitemController.do?doUpdateApp")
    Observable<BaseBean> doUpdateApp(@Query("sessionId") String sessionId,
                                     @Body RequestBody requestBody


    );



    @GET("tBiIcitemController.do?getItemInfo")
    Observable<GoodDetailBean> getCheck(@Query("sessionId") String sessionId,
                                        @Query("id") String id);



    @GET("tBiIcitemController.do?doDel")
    Observable<BaseBean> doDel(@Query("sessionId") String sessionId,
                               @Query("id") String id);

    @GET("tBiIcitemController.do?getPubIcItem")
    Observable<List<MenuItemBean>> getMaterialList(@Query("sessionId") String sessionId



    );
    @GET("tBiIcitemController.do?getIcItemInfo")
    Observable<IcItemInfoBean> getMaterialInfo(@Query("sessionId") String sessionId,
                                               @Query("id") String id



    );



}
