package com.qooence.code.usercenter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qooence.code.usercenter.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class AuthUser implements Serializable {

    private static final long serialVersionUID = -1581526899639506294L;

    @JsonIgnore
    private long id;

    private String username;

    private String password;

    @JsonIgnore
    private List<Role> roleList;

    private String roleIds;

    @JsonIgnore
    private Date lastPasswordResetDate;

    /**
     * 账户是否未过期
     */
    @JsonIgnore
    private boolean accountNonExpired;

    /**
     *  账户是否未锁定
     */
    @JsonIgnore
    private boolean accountNonLocked;

    /**
     * 密码是否未过期
     */
    @JsonIgnore
    private boolean credentialsNonExpired;

    /**
     * 账户是否激活
     */
    @JsonIgnore
    private boolean enabled;

    public AuthUser(Long id,String username,String password,List<Role> roleList){
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleList = roleList;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public AuthUser(){}
}
