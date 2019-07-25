package com.qhsoft.killrecord.net.message;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLHandshakeException;

/**
 * Description:
 * Author:lin
 * Date:2017-08-18
 */

public class AppFailureMessage implements FailureMessage<Throwable> {
    private Throwable t;

    public AppFailureMessage(Throwable throwable) {
        this.t = throwable;
    }

    @Override
    public boolean isServerFailure() {
        return false;
    }

    @Override
    public int getCode() {
        return -1000;
    }

    @Override
    public Throwable getExtra() {
        return t;
    }

    @Override
    public String getMessageText() {
        if (t instanceof JsonParseException ||
                t instanceof ParseException ||
                t instanceof JSONException) {
            return "数据解析异常";
        } else if (t instanceof HttpException) {
            return "网络请求异常,code: " + ((HttpException) t).code();
        } else if (t instanceof ConnectException) {
            return "网络连接异常";
        } else if (t instanceof SSLHandshakeException) {
            return "证书有问题,请检查证书";
        } else if (t instanceof SocketTimeoutException) {
            return "网络超时";
        }
        return "网络请求未响应";
    }


}
