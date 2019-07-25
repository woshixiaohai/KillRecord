package com.qhsoft.killrecord.model.remote.service;


import com.qhsoft.killrecord.model.remote.bean.ApkInfoBean;
import com.qhsoft.killrecord.model.remote.bean.LoginBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description:
 * Author:lin
 * Date:2017-09-12
 */

public interface PasscheckService {


    @POST("loginController.do?login_app")
    Observable<LoginBean> login(@Body RequestBody requestBody);


    @GET("tSysAppVersionController.do?checkAppVersion")
    Observable<ApkInfoBean> checkAppVersion(@Query("versionNum") String versionNum,
                                            @Query("packageName") String packageName
    );


}
