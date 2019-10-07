package com.qooence.code.usercenter.api;


import com.qooence.code.usercenter.dto.ResponseUserToken;
import com.qooence.code.usercenter.dto.AuthUser;

public interface IAuthService {
    /**
     * 注册用户
     * @return
     */
    AuthUser register(AuthUser authUser);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    ResponseUserToken login(String username, String password);

    /**
     * 登出
     * @param token
     */
    void logout(String token);

    /**
     * 刷新Token
     * @param oldToken
     * @return
     */
    ResponseUserToken refresh(String oldToken);

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    AuthUser getUserByToken(String token);
}
