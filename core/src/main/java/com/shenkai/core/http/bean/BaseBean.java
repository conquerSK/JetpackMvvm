package com.shenkai.core.http.bean;

/**
 * Created by shenkai on 2019/11/17.
 * desc:
 */
public class BaseBean<T> {
    private static final int SUCCESS = 1;
    private int code;
    private String message;
    private T data;

    public boolean isSuccessful() {
        return code == SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
