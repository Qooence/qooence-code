package com.qooence.code.usercenter.service.impl;

import com.qooence.code.usercenter.api.IUserService;
import com.qooence.code.usercenter.dto.UserLoginRequest;
import com.qooence.code.usercenter.dto.UserLoginResponse;
import com.qooence.code.usercenter.validator.UserValidator;
import org.apache.dubbo.config.annotation.Service;

@Service(interfaceName = "userService",version = "${dubbo.version}")
public class UserServiceImpl1 implements IUserService {

    public UserLoginResponse login(UserLoginRequest request) {
        //参数校验
        UserLoginResponse response = new UserLoginResponse();
        if (!UserValidator.checkUserLoginRequest(request)) {
            response.setCode("100001");
            response.setMemo("请求参数校验失败");
            return response;
        }
        if ("root".equals(request.getName()) && "root".equals(request.getPassword())) {
            response.setCode("000000");
            return response;
        }
        response.setCode("100002");
        response.setMemo("登录失败,帐号或密码错误");
        return response;
    }
}
