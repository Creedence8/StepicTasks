package ru.ya.creedence8.training.streamAPI;

import java.util.stream.IntStream;

/**
 * Created by Cole S' Offe on 28.11.2016.
 */
public class RandomNums {
    public static void main(String[] args) {
        pseudoRandomStream(13).limit(15)
                .forEach(x->System.out.print(x+" "));
    }

    public static IntStream pseudoRandomStream(int seed) {
        IntStream res = IntStream.iterate(seed, x->mid(Math.pow(x,2)) );
        return res; // your implementation here
    }

    public static int mid(double foo) {
        int raz4 =  (int) Math.floor( (foo % 10000)/10);
        return raz4;
    }
}
