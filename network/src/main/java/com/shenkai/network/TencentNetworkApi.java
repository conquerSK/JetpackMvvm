package com.shenkai.network;

import com.shenkai.network.base.NetworkApi;
import com.shenkai.network.beans.TencentBaseResponse;
import com.shenkai.network.errorhandler.ExceptionHandle;
import com.shenkai.network.utils.TecentUtil;

import java.io.IOException;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:shenkai
 * Time:2020/4/23 13:47
 * Description:
 */
public class TencentNetworkApi extends NetworkApi {
    private static volatile TencentNetworkApi sInstance;

    public static TencentNetworkApi getInstance() {
        if (sInstance == null) {
            synchronized (TencentNetworkApi.class) {
                if (sInstance == null) {
                    sInstance = new TencentNetworkApi();
                }
            }
        }
        return sInstance;
    }

    public static <T> T getService(Class<T> service) {
        return getInstance().getRetrofit(service).create(service);
    }

    @Override
    protected Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String timeStr = TecentUtil.getTimeStr();
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Source", "source");
                builder.addHeader("Authorization", TecentUtil.getAuthorization(timeStr));
                builder.addHeader("Date", timeStr);
                return chain.proceed(builder.build());
            }
        };
    }

    @Override
    protected <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                //response中code码不会0 出现错误
                if (response instanceof TencentBaseResponse && ((TencentBaseResponse) response).showapiResCode != 0) {
                    ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
                    exception.code = ((TencentBaseResponse) response).showapiResCode;
                    exception.message = ((TencentBaseResponse) response).showapiResError != null ? ((TencentBaseResponse) response).showapiResError : "";
                    throw exception;
                }
                return response;
            }
        };
    }

    @Override
    public String getFormal() {
        return "https://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/";
    }

    @Override
    public String getTest() {
        return "https://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/";
    }
}
