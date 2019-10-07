package com.qooence.code.usercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -145915848154046089L;

    private Long id;

    private String name;

    private String nameZh;
}
