package com.github.rguliamov.dreamtrip.app.infra.util.helperClasses;

/**
 * @author Guliamov Rustam
 */
public class A {
    private int age;

    private String name;

    public int id;

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
}
