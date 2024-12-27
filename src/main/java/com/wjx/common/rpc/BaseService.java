package com.wjx.common.rpc;

import com.wjx.common.result.ApiResult;

import java.io.Serializable;

public abstract class BaseService {
    protected <T extends Serializable> ApiResult<T> ok() {
        return ApiResult.Builder.<T>newBuilder().ok().build();
    }

    protected <T extends Serializable> ApiResult<T> ok(T data) {
        return ApiResult.Builder.<T>newBuilder().ok(data).build();
    }

    protected <T extends Serializable> ApiResult<T> fail(int errorNo, String msg) {
        return ApiResult.Builder.<T>newBuilder().fail(errorNo, msg).build();
    }
}
