package ru.ya.creedence8.Training.MailService;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Cole S' Offe on 12.11.2016.
 */
public class Mail {

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) {

//        Logger.getLogger("ru.ya.creedence8.Training").setLevel(Level.WARNING);
//        Logger.getLogger("ru.ya.creedence8.Training").setUseParentHandlers(false);
//        Logger.getLogger("ru.ya.creedence8.Training").addHandler(new ConsoleHandler());

        Logger spyLog = Logger.getLogger(Mail.class.getName());

        int minimalPrice = 500;
        int forbiddenStuffPrice = 400;

        Spy jamesBond = new Spy(spyLog);
        Thief robinHood = new Thief(minimalPrice);
        Inspector gadget = new Inspector();

        MailService[] woof = {jamesBond, robinHood, gadget};

        UntrustworthyMailWorker derpyHooves = new UntrustworthyMailWorker(woof);

        // launching process
        Sendable[] stuff = {
                new MailMessage("homo", "human", "lernu esperanton, hundino!"),
                new MailMessage(AUSTIN_POWERS, "fbi", "i need a new pink weapon"),
                new MailMessage("fbi", AUSTIN_POWERS, "'k, sure"),
                new MailPackage("dog", "wolf", new Package("some food", 500)),
                new MailPackage("clinton", "trump", new Package("money for imitation of freedom of choice", 1000)),
                new MailPackage("me", "you", new Package("wapons", 300))
        };

        for (Sendable sendable : stuff) {
            System.out.println("sending from " + sendable.getFrom() + " to " + sendable.getTo() + " " + sendable.toString());

            if (sendable instanceof MailMessage) {
                System.out.println(((MailMessage) sendable).getMessage());
                System.out.println(jamesBond.processMail(sendable));
            }
            if (sendable instanceof MailPackage) {
                System.out.println(((MailPackage) sendable).getContent().getContent());

                robinHood.processMail(sendable);
                System.out.println(robinHood.getStolenValue());

            }
//            derpyHooves.processMail(sendable);
        }
    }

    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }

    }

    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }

    }

    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }

    }

    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService {

        private MailService[] msArray;
        private RealMailService rms = new RealMailService();

        public UntrustworthyMailWorker(MailService[] ms){
            this.msArray = ms;
        }

        public RealMailService getRealMailService() {
            return rms;
        }

        @Override
        public Sendable processMail(Sendable mail) {;
            for (int i=0; i<msArray.length; i++) {
                mail = msArray[i].processMail(mail);
            }
            return rms.processMail(mail);
        }
    }

    public static class Spy implements MailService {
        public static Logger logger;

        public Spy(Logger logger) {
            this.logger = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                if (mail.getFrom().contains("Austin Powers") || mail.getTo().contains("Austin Powers") ) {
                    logger.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"", new Object[]
                            {mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()});
                } else {
                    logger.log(Level.INFO, "Usual correspondence: from {0} to {1}", new Object[] {mail.getFrom(), mail.getTo()});
                }
            }
            return null;
        }
    }

    public static class Thief implements MailService {
        private int price;
        private int stolenValue = 0;

        public Thief (int price) {
            this.price = price;
        }

        public int getStolenValue() {
            return stolenValue;
        }

        private void addStolenValue(int value) {
            this.stolenValue += value;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage && ((MailPackage) mail).getContent().getPrice() >= price) {
                addStolenValue(((MailPackage) mail).getContent().getPrice());

                return new MailPackage(mail.getFrom(), mail.getTo(), new Package("stones instead of " +
                        ((MailPackage) mail).getContent().getContent(),0));
            }
            return mail;
        }
    }

    public static class Inspector implements MailService{

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage pack = (MailPackage) mail;
                String str = pack.getContent().getContent();

                if (str.contains("weapons") || str.contains( "banned substance")) {
                    throw new IllegalPackageException();
                }
                if (str.contains("stones")) {
                    throw new StolenPackageException();
                }

                return mail;
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException () {super();}
        IllegalPackageException (String message) { super(message); }
        IllegalPackageException (String message, Throwable cause) {super(message, cause);}
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException () {super();}
        StolenPackageException (String message) { super(message); }
        StolenPackageException (String message, Throwable cause) {super(message, cause);}
    }

}

