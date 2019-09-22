package com.qooence.code.usercenter.api;

import com.qooence.code.usercenter.dto.UserLoginRequest;
import com.qooence.code.usercenter.dto.UserLoginResponse;

public interface IUserService {

    /**
     * 登录
     *
     * @param request
     * @return
     */
    UserLoginResponse login(UserLoginRequest request);
}
