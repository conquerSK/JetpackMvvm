package com.shenkai.core.http.exception;

/**
 * Created by shenkai on 2019/11/17.
 * desc:
 */
public class ResponseThrowable extends Exception {
    private int code;
    private String message;

    public ResponseThrowable(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public ResponseThrowable(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
