package com.qooence.code.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper extends BaseMapper {

    boolean insertRole(@Param("userId") Long userId,@Param("roleId") Long roleId);
}
