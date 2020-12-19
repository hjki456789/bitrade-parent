package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

import java.io.*;

public class ResponseResult<T> implements Serializable {
    private final T data;

    private ResponseResult(final T data) {
        this.data = data;
    }

    public static ResponseResult success() {
        return success(new Object());
    }

    public static <T> ResponseResult<T> success(final T data) {
        return (ResponseResult<T>) build((Object) data);
    }

    public static <T> ResponseResult<T> build(final T data) {
        return new ResponseResult<T>(data);
    }
}
