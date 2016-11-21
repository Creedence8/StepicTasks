package ru.ya.creedence8.Training;

/**
 * Created by Cole S' Offe on 08.11.2016.
 */
public class AsciiCharSequence implements CharSequence {

    public byte[] array;

    AsciiCharSequence(byte[] value){
        array = value;
    }

    @Override
    public int length(){
       return this.array.length;
    }

    @Override
    public char charAt(int index){
        return (char) this.array[index];
    }

    @Override
    public CharSequence subSequence(int start, int end){
        byte[] res = new byte[end - start];
        System.arraycopy(array, start, res, 0 , end-start);
        return new AsciiCharSequence(res);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for (byte foo:array){
            res.append((char) foo);
        }
        return res.toString();
    }
}
