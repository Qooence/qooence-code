package com.qooence.code.usercenter.service;

import com.qooence.code.usercenter.entity.Role;
import com.qooence.code.usercenter.entity.UserDetail;
import com.qooence.code.usercenter.mapper.AuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 登陆身份认证
 */
@Slf4j
@Component(value="CustomUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public UserDetail loadUserByUsername(String name) throws UsernameNotFoundException {
        log.info("loadUserByUsername(String name)=====>[{}]",name);
        UserDetail userDetail = authMapper.findByUsername(name);
        if (userDetail == null) {
            throw new UsernameNotFoundException(String.format("No userDetail found with username '%s'.", name));
        }
        Role role = authMapper.findRoleByUserId(userDetail.getId());
        userDetail.setRole(role);
        return userDetail;
    }
}
