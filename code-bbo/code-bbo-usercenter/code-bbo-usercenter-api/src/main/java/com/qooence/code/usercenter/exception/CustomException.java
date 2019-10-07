package com.qooence.code.usercenter.exception;

import com.qooence.code.usercenter.common.ResultJson;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;


/**
 * 自定义异常
 */
@Data
public class CustomException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8281271866156385340L;

    private ResultJson resultJson;

    public CustomException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }

    public CustomException() {

    }
}
