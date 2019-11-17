package com.shenkai.core.http.interceptor;

import com.shenkai.core.http.AppUtils;
import com.shenkai.core.http.HttpUtils;
import com.shenkai.core.http.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shenkai on 2019/11/16.
 * desc:
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkUtil.isNetworkAvailable(AppUtils.getContext())) {
            // 有网络时, 缓存1小时
            int maxAge = 60 * 60;
            request = request.newBuilder()
                    .removeHeader("User-Agent")
                    .header("User-Agent", HttpUtils.getUserAgent())
                    .build();//用不上可以删除

            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            // 无网络时，缓存为7天
            int maxStale = 60 * 60 * 24 * 7;
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .removeHeader("User-Agent")
                    .header("User-Agent", HttpUtils.getUserAgent())
                    .build();

            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
