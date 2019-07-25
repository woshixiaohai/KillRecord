package com.qhsoft.killrecord.net.message;

/**
 * Description:
 * Author:lin
 * Date:2017-08-18
 */

public interface FailureMessage<T> {


    boolean isServerFailure();


    int getCode();


    T getExtra();


    String getMessageText();
}
