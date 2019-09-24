package com.qooence.code.usercenter.service;

import com.qooence.code.usercenter.entity.SysUser;

/**
 * 用户服务接口
 */
public interface UserService {

    SysUser getByUsername(String username);
}
