package ru.ya.creedence8.Training;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.ws.policy.jaxws.SafePolicyReader;
import ru.ya.creedence8.Training.MailService.Mail;
import sun.plugin.javascript.navig.Array;

import java.io.*;
import java.nio.charset.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.*;

/**
 * Created by Cole S' Offe on 08.11.2016.
 */
public class Main {

    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeInt(3);
        Animal[] animals = new Animal[] {new Animal("Barsik"),
                new Animal("Jack"),
                new Animal("Michael"),};
        for (Animal foo : animals) {
            oos.writeObject(foo);
        }

        Animal[] foo = (deserializeAnimalArray(baos.toByteArray()));
        for (int i=0; i<foo.length; i++) {
            System.out.println(foo[i].equals(animals[i]));
        }
    }

    public static Animal[] deserializeAnimalArray(byte[] data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            Animal[] res = new Animal[ois.readInt()];
            for (int i=0; i<res.length; i++) {
                res[i] = (Animal) ois.readObject();

            }
            return res;

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }

    public static void sumNum() {
        Reader reader = new StringReader("-1e3\n" +
                "18 .111 11bbb");
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("[\n ]");
        String str;
        double sum=0;
        while (scan.hasNext()) {
            str = scan.next();
            if (str.equals("end")) {
                break;
            }
            try {
                sum += Double.parseDouble(str);
            } catch (Exception e) {
//                LOGGER.log(Level.INFO,"",e);
            }
        }
        System.out.printf("%.6f",sum);
    }

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        String res = "";
        Reader reader = new InputStreamReader(inputStream, charset);
        int read = reader.read();
        while (read!=-1){
            res+=(char) read;
            read = reader.read();
        }
        return res;
    }

    public static void ChangeEndStr() throws IOException {
        int read = System.in.read();
        int read2 = System.in.read();
        while (read!=-1) {
            if (byteIs13(read) && byteIs10(read2)) {
                System.out.write((byte) read2);
                read = System.in.read();
                read2 = System.in.read();
            } else {
                System.out.write((byte) read);
                read = read2;
                read2 = System.in.read();
            }
        }
        System.out.flush();
    }

    public static boolean byteIs13(int foo) {
        if ((byte) foo == 13) {
            return true;
        }
        return false;
    }

    public static boolean byteIs10 (int foo) {
        if ((byte) foo == 10) {
            return true;
        }
        return false;
    }

    public static void anMet(){
        System.out.println(getCallerClassAndMethodName());
    }

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer ta : analyzers){
            if (ta.processText(text) != Label.OK) {
                return ta.processText(text);
            }

        }
        return Label.OK;
    }

    public static double sqrt(double x) {
        if (x<0){
            throw new IllegalArgumentException("Expected non-negative number, got " + x);
        }
        return Math.sqrt(x);
    }

    public static String getCallerClassAndMethodName() {
        Throwable error = new Throwable();
        StackTraceElement[] mass = error.getStackTrace();
        if (mass.length<=2) return null;
        return mass[2].getClassName() +"#"+ mass[2].getMethodName();
    }

    private static void configureLogging() {
        Logger.getLogger("org.stepic.java.logging.ClassA").setLevel(Level.ALL);
        Logger.getLogger("org.stepic.java.logging.ClassB").setLevel(Level.WARNING);
        Logger.getLogger("org.stepic.java").setLevel(Level.ALL);
        Logger.getLogger("org.stepic.java").setUseParentHandlers(false);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new XMLFormatter());
        Logger.getLogger("org.stepic.java").addHandler(ch);
    }

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int cn = 0;
        int read = inputStream.read();
            while (read!=-1){
                System.out.println(read);
                cn = Integer.rotateLeft(cn, 1) ^ read;
                read = inputStream.read();
            }
            return cn;

    }
}

