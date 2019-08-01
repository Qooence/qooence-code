package com.qooence.code.clone;

import com.qooence.code.Person;

import java.io.*;

public class Student extends Person implements Serializable {

    private static final long serialVersionUID = -1180679781747732822L;

    private String markLevel;

    private Teacher teacher;

    public String getMarkLevel() {
        return markLevel;
    }

    public void setMarkLevel(String markLevel) {
        this.markLevel = markLevel;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Object deepClone(){
        try {
            // 将对象写到流里
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            // 从流里读出来
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (ois.readObject());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Student{" +
                "markLevel='" + markLevel + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
