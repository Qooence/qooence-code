package com.qooence.code.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -2219153547505995404L;

    private Long id;
    private String name;
    private Integer Age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Age=" + Age +
                '}';
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        Age = age;
    }

    public User() {
    }
}
