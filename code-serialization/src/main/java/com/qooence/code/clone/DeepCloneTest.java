package com.qooence.code.clone;

public class DeepCloneTest {

    public static void main(String[] args) {
        Student student = new Student();
        Teacher teacher = new Teacher();
        teacher.setClassName("一班");
        teacher.setName("Teacher");
        teacher.setAge(45);
        teacher.setSex("男");

        student.setMarkLevel("AA");
        student.setName("qooence");
        student.setAge(25);
        student.setSex("男");
        student.setTeacher(teacher);

        Student cloneStudent = (Student) student.deepClone();
        cloneStudent.setName("clone");

        System.out.println(student.getName());
        System.out.println(cloneStudent.getName());
    }
}
