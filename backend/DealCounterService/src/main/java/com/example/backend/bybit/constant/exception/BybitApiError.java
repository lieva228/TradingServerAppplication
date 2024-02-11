package com.example.backend.bybit.constant.exception;

import com.traidl.scalpbot.bybit.constant.BybitApiConstants;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;


@Getter
public class BybitApiError {

    private int code;

    private String msg;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BybitApiConstants.TO_STRING_BUILDER_STYLE)
                .append("code", code)
                .append("msg", msg)
                .toString();
    }
}