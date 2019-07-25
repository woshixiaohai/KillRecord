package com.qhsoft.killrecord.net;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.qhsoft.killrecord.net.gson.GsonConverterFactory;
import com.qhsoft.killrecord.util.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;


/**
 * Description:
 * Author:lin
 * Date:2017-08-18
 */

public class HttpHandler {


    private static Retrofit retrofit;
    private static int TIMEOUT = 30;
    private static Retrofit getInstance() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient
                    .Builder()
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(new RequestInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpConfig.baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static <T> T create(Class<T> service) {
        return getInstance().create(service);
    }
    public static void clearRetrofit(){
        retrofit=null;
    }


    private static class RequestInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            Logger.d(HttpHandler.class.getSimpleName(), request.url().toString());
            return chain.proceed(request);
        }
    }




}
