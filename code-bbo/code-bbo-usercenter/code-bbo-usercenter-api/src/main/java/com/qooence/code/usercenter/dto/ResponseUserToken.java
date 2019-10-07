package com.qooence.code.usercenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class ResponseUserToken implements Serializable {

    private static final long serialVersionUID = 5463136773846930801L;

    private String token;

    private AuthUser authUser;
}
