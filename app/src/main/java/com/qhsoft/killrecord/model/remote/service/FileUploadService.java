package com.qhsoft.killrecord.model.remote.service;

import com.qhsoft.killrecord.model.remote.bean.FileDownloadResultBean;
import com.qhsoft.killrecord.model.remote.bean.FileUploadResultBean;

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
 * 文件用json上传和下载，只适用小文件
 *
 */


public interface FileUploadService {
    /**
     * 附件上传
     *
     */
    @POST("fileServerController.do?doUpload")
    Observable<FileUploadResultBean> fileUpload(@Query("sessionId") String sessionId, @Body RequestBody requestBody);

    /**
     * 附件下载
     */
    @GET("fileServerController.do?doDownload")
    Observable<FileDownloadResultBean> fileDownload(@Query("sessionId") String sessionId, @Query("fileId") String fileId);



}
