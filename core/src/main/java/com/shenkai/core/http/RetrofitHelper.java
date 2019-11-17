package com.shenkai.core.http;

import android.util.Log;

import com.shenkai.core.http.interceptor.CacheInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shenkai on 2019/11/16.
 * desc:
 */
public class RetrofitHelper {
    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 15;
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.i("RetrofitLog:", "retrofitBack=" + message);
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);
    private static final Cache cache = new Cache(new File(AppUtils.getContext().getCacheDir(), "HttpCache"),
            1024 * 1024 * 50);// 指定缓存路径,缓存大小 50Mb
    private static Retrofit retrofit;
    //服务端根路径
    public static String baseUrl = "https://www.oschina.net/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(new CacheInterceptor())
            .cache(cache)
            //time out
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            //失败重连
            .retryOnConnectionFailure(true)
            .build();

    public static <T> T create(Class<T> service) {
        if (retrofit == null) {
           retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(service);
    }

    /**
     * 针对多baseurl情况使用本方法
     */
    public static <T> T createApi(Class<T> clazz, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
}
