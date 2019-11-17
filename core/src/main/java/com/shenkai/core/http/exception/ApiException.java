package com.shenkai.core.http.exception;

/**
 * Created by shenkai on 2019/11/17.
 * desc:
 */
public class ApiException extends ResponseThrowable {
    public ApiException(int code, String message) {
        super(code, message);
    }
}
