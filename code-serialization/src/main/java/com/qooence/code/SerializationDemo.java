package com.qooence.code;

import java.io.*;

public class SerializationDemo {

    public static void main(String[] args) {
        // 序列化
        serializationPerson();

        // 返序列化
        deSerializationPerson();
    }

    // 序列化
    private static void serializationPerson(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("person")));
            Person person = new Person();
            person.setName("Qooence");
            person.setAge(25);
            person.setSex("男");
            oos.writeObject(person);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 反序列化
    private static void deSerializationPerson(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("person")));
            Person person = (Person) ois.readObject();
            System.out.println(person);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
