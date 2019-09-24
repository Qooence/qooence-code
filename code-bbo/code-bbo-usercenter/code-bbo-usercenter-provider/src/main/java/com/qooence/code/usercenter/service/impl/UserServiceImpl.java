package com.qooence.code.usercenter.service.impl;

import com.qooence.code.usercenter.entity.SysUser;
import com.qooence.code.usercenter.repository.SysUserRepository;
import com.qooence.code.usercenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
}