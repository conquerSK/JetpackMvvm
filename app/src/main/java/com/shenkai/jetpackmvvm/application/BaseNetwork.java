package com.shenkai.jetpackmvvm.application;

import android.app.Application;

import com.shenkai.jetpackmvvm.BuildConfig;
import com.shenkai.network.base.INetworkRequiredInfo;

/**
 * Author:shenkai
 * Time:2020/4/23 14:20
 * Description:
 */
public class BaseNetwork implements INetworkRequiredInfo {
    private Application mApplication;

    public BaseNetwork(Application application) {
        this.mApplication = application;
    }

    @Override
    public String getAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public String getAppVersionCode() {
        return String.valueOf(BuildConfig.VERSION_CODE);
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public Application getApplicationContext() {
        return mApplication;
    }
}
