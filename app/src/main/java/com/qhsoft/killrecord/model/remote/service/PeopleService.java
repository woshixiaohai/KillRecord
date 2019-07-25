package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.PeopleDetailBean;
import com.qhsoft.killrecord.model.remote.bean.PeopleListBean;
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


public interface PeopleService {

    @POST("tGdGridStaffController.do?datagrid&type=2")
    Observable<PeopleListBean> getList(@Query("sessionId") String sessionId,
                                       @Query("name") String name,
                                       @Query("page") int page,
                                       @Query("rows") int rows

    );

    @POST("tGdGridStaffController.do?datagrid&type=2")
    Observable<PeopleListBean> getCoronerList(@Query("sessionId") String sessionId,
                                              @Query("name") String name,
                                              @Query("certificateType") String certificateType,
                                              @Query("page") int page,
                                              @Query("rows") int rows

    );

    @POST("tGdGridStaffController.do?doAddOrUpdateApp")
    Observable<BaseBean> doAddOrUpdateApp(@Query("sessionId") String sessionId,
                                          @Body RequestBody requestBody


    );

    @GET("tGdGridStaffController.do?doGetApp")
    Observable<PeopleDetailBean> getDetail(@Query("sessionId") String sessionId,
                                           @Query("id") String id


    );

    @POST("tGdGridStaffController.do?doBatchDel")
    Observable<BaseBean> doBatchDel(@Query("sessionId") String sessionId,
                                    @Query("ids") String ids


    );


}
