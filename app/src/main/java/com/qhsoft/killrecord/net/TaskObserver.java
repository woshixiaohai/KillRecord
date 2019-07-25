package com.qhsoft.killrecord.net;


import com.qhsoft.killrecord.net.message.AppFailureMessage;
import com.qhsoft.killrecord.net.message.FailureMessage;
import com.qhsoft.killrecord.net.message.ServerFailureMessage;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author:lin
 * Date:2017-09-07
 */

public abstract class TaskObserver<T> implements Observer<T> {

    @Override
    public final void onNext(T value) {
        if (value instanceof BaseBean) {
            BaseBean baseBean = (BaseBean) value;
            if (baseBean.isSuccess()) {
                onSuccess(value);
            }else{
                onFailure(new ServerFailureMessage(baseBean));
            }
            onStop();
        } else {
            onSuccess(value);
        }

    }

    @Override
    public final void onError(Throwable e) {
        e.printStackTrace();
        onFailure(new AppFailureMessage(e));
        onStop();
    }

    @Override
    public final void onComplete() {
    }

    public void onStop() {

    }

    public abstract void onSuccess(T value);

    public  void onFailure(FailureMessage failureMessage){};

    @Override
    public void onSubscribe(Disposable d) {

    }
}
