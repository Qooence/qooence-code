package com.qooence.code.usercenter.dto;

import java.io.Serializable;

public class Response implements Serializable {


    private static final long serialVersionUID = 315800721430687441L;

    private String code;

    private String memo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
