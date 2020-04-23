package com.shenkai.network.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author:shenkai
 * Time:2020/4/23 14:11
 * Description:
 */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T t);

    protected abstract void onFailure(Throwable e);
}
