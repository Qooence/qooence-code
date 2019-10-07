package com.qooence.code.usercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Data
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 466059863723735942L;

    @JsonIgnore
    private Long id;

    @Size(min=5, max=20)
    private String name;

    @Size(min=6, max=20)
    private String password;
}
