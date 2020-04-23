package com.shenkai.network.base;

import android.app.Application;

/**
 * Author:shenkai
 * Time:2020/4/23 11:03
 * Description:
 */
public interface INetworkRequiredInfo {
    String getAppVersionName();
    String getAppVersionCode();
    boolean isDebug();
    Application getApplicationContext();
}
