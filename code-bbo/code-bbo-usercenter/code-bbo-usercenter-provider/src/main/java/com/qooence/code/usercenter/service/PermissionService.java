package com.qooence.code.usercenter.service;

import com.qooence.code.usercenter.entity.SysPermission;

import java.util.List;

/**
 * 权限服务接口
 */
public interface PermissionService {

    List<SysPermission> findByUserId(Integer userId);

}
