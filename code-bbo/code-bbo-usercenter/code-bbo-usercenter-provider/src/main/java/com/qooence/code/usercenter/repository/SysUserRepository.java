package com.qooence.code.usercenter.repository;

import com.qooence.code.usercenter.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户 jpa Repository 相当于Mapper
 */
public interface SysUserRepository extends JpaSpecificationExecutor<SysUser>, JpaRepository<SysUser, Integer> {
    SysUser findByUsername(String username);
}
