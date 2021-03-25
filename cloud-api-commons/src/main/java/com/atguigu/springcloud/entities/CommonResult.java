package com.atguigu.springcloud.entities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class CommonResult<T>
{
    private Integer code;

    private String message;

    private T data;

    public CommonResult() {
    }

    public CommonResult(final Integer code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CommonResult<?> that = (CommonResult<?>) o;
        return Objects.equal(code, that.code) && Objects.equal(message, that.message)
            && Objects.equal(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code, message, data);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("code", code)
                          .add("message", message)
                          .add("data", data)
                          .toString();
    }
}
