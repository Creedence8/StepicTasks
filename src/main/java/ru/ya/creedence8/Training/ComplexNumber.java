package ru.ya.creedence8.Training;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 * Created by Cole S' Offe on 07.11.2016.
 */
public final class ComplexNumber {
    private final double re;
    private final double im;

    private static final Logger logger = Logger.getLogger(ComplexNumber.class.getName());

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static void main(String[] args) {
        setLoggerSettings();

        ComplexNumber object = new ComplexNumber(10.00001, 5.4);
        logger.log(Level.FINE, "first log");
        ComplexNumber object2 = new ComplexNumber(10.00001, 5.4);

        System.out.println(object.equals(object2));
        logger.log(Level.SEVERE, "oh, way");

        System.out.println(object.hashCode());
        System.out.println(object2.hashCode());
    }

    private static void setLoggerSettings(){
        Logger.getLogger("ru.ya.creedence8.Training.ComplexNumber").setLevel(Level.ALL);
        Logger.getLogger("ru.ya.creedence8.Training.ComplexNumber").setUseParentHandlers(true);
        Logger.getLogger("ru.ya.creedence8.Training").setLevel(Level.ALL);
        Logger.getLogger("ru.ya.creedence8.Training").setUseParentHandlers(false);

        ConsoleHandler ch2 = new ConsoleHandler();
        ch2.setLevel(Level.SEVERE);
        ch2.setFormatter(new XMLFormatter());
        Logger.getLogger("ru.ya.creedence8.Training.ComplexNumber").addHandler(ch2);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        Logger.getLogger("ru.ya.creedence8.Training").addHandler(ch);
    }

    @Override
    public boolean equals(Object o) {
        logger.log(Level.WARNING, "log equals");

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexNumber that = (ComplexNumber) o;

        if (Double.compare(that.re, re) != 0) return false;
        return Double.compare(that.im, im) == 0;

    }

    @Override
    public int hashCode() {
        logger.log(Level.FINEST, "log hashCode");

        int result;
        long temp;
        temp = Double.doubleToLongBits(re);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(im);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }
}
