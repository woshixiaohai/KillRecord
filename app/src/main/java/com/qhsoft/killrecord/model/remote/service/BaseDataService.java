package com.qhsoft.killrecord.model.remote.service;


import com.qhsoft.killrecord.model.remote.bean.TypeCodeBean;
import com.qhsoft.killrecord.model.remote.bean.TypeGroupCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Description:
 * Author:lin
 * Date:2017-09-18
 */

public interface BaseDataService {



    /**
     * 获取字典名称
     * @param typeGroupCode //字典组代码
     * @param typeCode 字典项代码
     */
    @GET("systemController.do?getTypeNameByCode")
    Observable<TypeCodeBean> getTypeCode(@Query("typeGroupCode") String typeGroupCode,
                                         @Query("typeCode") String typeCode,
                                         @Query("sessionId") String sessionId);


    /**
     * 获取字典名称
     * @param typeGroupCode //字典组代码
     */
    @GET("systemController.do?getTypeGroupByCode")
    Observable<TypeGroupCodeBean> getTypeGroupCode(@Query("typeGroupCode") String typeGroupCode,
                                                   @Query("sessionId") String sessionId);




}