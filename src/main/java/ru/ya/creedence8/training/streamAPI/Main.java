package ru.ya.creedence8.training.streamAPI;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.function.*;

/**
 * Created by Cole S' Offe on 29.11.2016.
 */
public class Main {

    public static void wordsFrequency() {

        Scanner scan = new Scanner(System.in)
                .useDelimiter("[^\\p{L}\\p{Digit}_]+");

        Map<String, Integer> map = new TreeMap<>();
        String buff;

        while (scan.hasNext()) {
            buff = scan.next().toLowerCase();
            if (buff.equals("end")) {
                break;
            }
            if (map.keySet().contains(buff)) {
                map.put(buff, map.get(buff)+1);
            } else {
            map.put(buff, 1);
            }
        }

        Stream<Map.Entry<String, Integer>> stream =  map.entrySet().stream();
                stream.sorted(Comparator.comparing( x-> x.getValue(),
                        (x,y)-> y-x))
                .limit(10)
                .forEach(x-> System.out.println(x.getKey()));
    }

    public static void main(String[] args) {
        // Random variables
        String randomFrom = "stroka1"; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "stroka2";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

// Создание списка из трех почтовых сообщений.
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard"): "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft"): "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!"): "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

// Создание почтового сервиса.
        MailService<String> mailService = new MailService<>();

// Обработка списка писем почтовым сервисом
        messages.stream().forEachOrdered(mailService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список сообщений, которые были ему отправлены
        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ): "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ): "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()): "wrong mailService mailbox content (3)";


// Создание списка из трех зарплат.
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

// Создание почтового сервиса, обрабатывающего зарплаты.
        MailService<Integer> salaryService = new MailService<>();

// Обработка списка зарплат почтовым сервисом
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список зарплат, которые были ему отправлены.
        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)): "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)): "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)): "wrong salaries mailbox content (3)";
    }


    public static class MailMessage implements Sendable<String> {
        private String from;
        private String to;
        private String message;

        public MailMessage(String from, String to, String message) {
            this.from = from;
            this.to =to;
            this.message=message;
        }
        public String getFrom() {return this.from;}
        public String getTo() {return this.to;}
        public String getContent() {return this.message;}
    }

    public static class Salary implements Sendable<Integer>{
        private String company;
        private String name;
        private Integer salary;

        public Salary(String company, String name, int salary) {
            this.company = company;
            this.name = name;
            this.salary = salary;}

        public String getTo() { return name;}
        public Integer getContent() {return salary;}
        }


    public static class MailService<T> implements Consumer<Sendable<T>> {
        public Map<String, List<T>> mailBox = new HashMap<String, List<T>>() {
            @Override
            public List<T> get(Object key) {
                return super.getOrDefault(key, new LinkedList<T>());
            }
        };

        public Map<String, List<T>> getMailBox() {
            return mailBox;
        }

        @Override
        public void accept(Sendable<T> t) {
            if (t instanceof MailMessage) {
                MailMessage obj = (MailMessage) t;

                mailBox.put(obj.getTo(), mailBox.get(obj.getTo()));
                mailBox.get(obj.getTo()).add((T) obj.getContent());

            }
            if (t instanceof Salary) {
                Salary obj = (Salary) t;
                if (mailBox.keySet().contains(obj.getTo())) {
                    mailBox.get(obj.getTo()).add((T) obj.getContent());
                } else {
                    mailBox.put(obj.getTo(), Arrays.asList((T) obj.getContent()));
                }

            }
        }

    }


    public interface Sendable<T> {

    }

}
