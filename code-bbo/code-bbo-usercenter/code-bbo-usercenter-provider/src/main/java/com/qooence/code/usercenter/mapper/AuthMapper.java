package com.qooence.code.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qooence.code.usercenter.entity.Role;
import com.qooence.code.usercenter.entity.UserDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper extends BaseMapper {
    /**
     * 根据用户名查找用户
     * @param name
     * @return
     */
    UserDetail findByUsername(@Param("name") String name);

    /**
     * 创建新用户
     * @param userDetail
     */
    void insert(UserDetail userDetail);

    /**
     * 创建用户角色
     * @param userId
     * @param roleId
     * @return
     */
    int insertRole(@Param("userId") long userId, @Param("roleId") long roleId);

    /**
     * 根据角色id查找角色
     * @param roleId
     * @return
     */
    Role findRoleById(@Param("roleId") long roleId);

    /**
     * 根据用户id查找该用户角色
     * @param userId
     * @return
     */
    Role findRoleByUserId(@Param("userId") long userId);
}
