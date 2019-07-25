package com.qhsoft.killrecord.net.message;


import com.qhsoft.killrecord.net.BaseBean;

/**
 * Description:
 * Author:lin
 * Date:2017-08-18
 */

public class ServerFailureMessage<T extends BaseBean> implements FailureMessage<BaseBean> {
    private BaseBean data;

    public ServerFailureMessage(BaseBean data) {
        this.data = data;
    }

    @Override
    public boolean isServerFailure() {
        return true;
    }

    @Override
    public int getCode() {
        return 400;
    }


    @SuppressWarnings("unchecked")
    @Override
    public T getExtra() {
        return (T) data;
    }

    @Override
    public String getMessageText() {
        return data.getMsg();
    }
}
