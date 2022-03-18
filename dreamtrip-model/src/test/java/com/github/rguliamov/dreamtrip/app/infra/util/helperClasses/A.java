package com.github.rguliamov.dreamtrip.app.infra.util.helperClasses;

/**
 * @author Guliamov Rustam
 */
public class A {
    private int age;

    private String name;

    private int id;

    private long serialUID;

    public A() {
        id = 1;
    }

    public A(int age, String name, int id, long serialUID) {
        this.age = age;
        this.name = name;
        this.id = id;
        this.serialUID = serialUID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSerialUID() {
        return serialUID;
    }

    public void setSerialUID(long serialUID) {
        this.serialUID = serialUID;
    }
}
