package com.qooence.code.usercenter.repository;

import com.qooence.code.usercenter.entity.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 角色权限 Repository 相当于Mapper
 */
public interface SysRolePermissionRepository extends JpaSpecificationExecutor<SysRolePermission>, JpaRepository<SysRolePermission, Integer> {

    @Query(value = "SELECT * FROM sys_role_permission WHERE role_id IN (:roleIds)", nativeQuery = true)
    List<SysRolePermission> findByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
