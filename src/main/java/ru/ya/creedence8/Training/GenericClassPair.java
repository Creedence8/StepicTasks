package ru.ya.creedence8.Training;

import java.util.Objects;

/**
 * Created by Cole S' Offe on 23.11.2016.
 */
public class GenericClassPair {

    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        System.out.println(i +" "+s+" "+mustBeTrue+" "+mustAlsoBeTrue);
    }
}

class Pair <T, W> {
    private T valueT;
    private W valueW;

    private Pair() {this.valueT = null; this.valueW = null;}
    private Pair(T valueT, W valueW) { this.valueT = valueT; this.valueW = valueW;}
    public static <T, W> Pair<T , W> of(T valueT, W valueW) {return new Pair(valueT, valueW);}

    public T getFirst() {return valueT;}
    public W getSecond() {return valueW;}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) {return true;}
        if (!(obj instanceof Pair)) {return false;}
        Pair<?,?> foo = (Pair<?,?>) obj;
        return Objects.equals(this.getFirst(), foo.getFirst()) && Objects.equals(this.getSecond(), foo.getSecond());
    }

    @Override
    public int hashCode() { return Objects.hash(valueT, valueW);}
}
