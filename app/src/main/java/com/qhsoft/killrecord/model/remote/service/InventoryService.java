package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.HandlerProductListBean;
import com.qhsoft.killrecord.model.remote.bean.ProductStoreListBean;
import com.qhsoft.killrecord.model.remote.bean.TobeKillListBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description:
 * Author:lin
 * Date:2017-09-29
 */


public interface InventoryService {
    /**
     * 待宰畜禽
     *
     */
    @POST("tBruteApprRecController.do?datagridInv")
    Observable<TobeKillListBean> getTobekillList(@Query("sessionId") String sessionId,
                                                 @Query("billno") String billno,
                                                 @Query("supplyname") String supplyname,
                                                 @Query("page") int page,
                                                 @Query("rows") int rows

            );



    @POST("tBruteRuleAftController.do?datagridAnimInv")
    Observable<ProductStoreListBean> getProductStoreList(@Query("sessionId") String sessionId,
                                                         @Query("billno") String billno,
                                                         @Query("supplyname") String supplyname,
                                                         @Query("page") int page,
                                                         @Query("rows") int rows

    );
    @POST("tBruteRuleAftController.do?datagridHandleInv")
    Observable<HandlerProductListBean> getHandlerProductList(@Query("sessionId") String sessionId,
                                                             @Query("billno") String billno,
                                                             @Query("supplyname") String supplyname,
                                                             @Query("page") int page,
                                                             @Query("rows") int rows

    );





}
