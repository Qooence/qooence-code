package com.qooence.code.usercenter.dto;

import java.io.Serializable;

public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 7700134130855539805L;

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginRequest{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
