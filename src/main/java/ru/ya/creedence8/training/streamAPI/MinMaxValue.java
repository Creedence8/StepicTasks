package ru.ya.creedence8.training.streamAPI;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Cole S' Offe on 28.11.2016.
 */
public class MinMaxValue {

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<T> list = stream.collect(Collectors.toList());
        if (list.size()==0) {
            minMaxConsumer.accept(null, null);
        } else {
        Collections.sort(list, order);
        minMaxConsumer.accept(list.get(0),list.get(list.size()-1) );
        }
    }

    public static void main(String[] args) {

    }
}
