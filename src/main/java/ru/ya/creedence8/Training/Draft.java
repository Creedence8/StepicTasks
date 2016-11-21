package ru.ya.creedence8.Training;

import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Created by Cole S' Offe on 07.11.2016.
 */
public class Draft {

    public static void main(String[] args) throws IOException {

        int[] test = new int[5];
        test[4] = 5;
        for (int i=0;i<test.length; i++) {
            test[i] = 1;
        }
        System.out.println( Arrays.toString(test));

//        byte[] bar = "Ы".getBytes();
//        for (byte foo : bar) {
//            System.out.print(Byte.toUnsignedInt(foo) + " ");
//        }

    }
}



