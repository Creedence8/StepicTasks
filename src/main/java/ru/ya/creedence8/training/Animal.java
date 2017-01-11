package ru.ya.creedence8.training;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Cole S' Offe on 19.11.2016.
 */
public class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }
}
