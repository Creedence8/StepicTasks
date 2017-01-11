package ru.ya.creedence8.training;

/**
 * Created by Cole S' Offe on 07.11.2016.
 */
public class Draft {

    public static void main(String[] args) {
        A a = new B();
        a.testFunc();
        System.out.println(a.getClass());
    }
}

class A {

    public void testFunc() {
        System.out.println("instance of class A");
    }
    String t;

}

class B extends A {
    int value = 20;

    @Override
    public void testFunc() {
        System.out.println("instance of class B");
    }
}

