package com.shenkai.jetpackmvvm.application;

import android.app.Application;

import com.shenkai.network.base.NetworkApi;

/**
 * Author:shenkai
 * Time:2020/4/23 14:19
 * Description:
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkApi.init(new BaseNetwork(this));
    }
}
