package com.shenkai.core.http;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Created by shenkai on 2019/11/16.
 * desc:
 */
public class AppUtils {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(final Context context) {
        AppUtils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("should be initialized in application");
    }
}
