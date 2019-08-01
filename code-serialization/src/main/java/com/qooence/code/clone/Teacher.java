package com.qooence.code.clone;

import com.qooence.code.Person;

import java.io.Serializable;


public class Teacher extends Person implements Serializable {

    private static final long serialVersionUID = 4774935985625523396L;

    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
