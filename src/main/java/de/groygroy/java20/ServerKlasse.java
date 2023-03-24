package de.groygroy.java20;


import jdk.incubator.concurrent.ScopedValue;

import java.util.Locale;
import java.util.concurrent.Future;

/**
 * Beispiel für Scoped Value, JEP 429
 */
public class ServerKlasse {
    public final static ScopedValue<String> SCOPED_VALUE = ScopedValue.newInstance();

    public static void main(String... args) {

        System.out.println("-- Zugriff mit Scope --");
        String[] anwendende={"Lu-Tze", "Lobsang Ludd", "Susan Sto Helit"};

        for (var anwender : anwendende) {
            // Variable im neuen Scope setzen und Code starten
            jdk.incubator.concurrent.ScopedValue.where(SCOPED_VALUE, anwender).run(new Aufgabe());
        }
        System.out.println("-- Zugriff ohne Scope --");
        new Thread(new Aufgabe()).start();

        var l0 = new Locale("de", "DE");
        var l1 = Locale.of("de", "DE");
        var l2 = new Locale.Builder().setLanguage("de").setRegion("DE").build();

        
        System.out.println("l0 " + l0 + ", l1 " + l1 + ", l2 " + l2);

    }

    static class Aufgabe implements Runnable {
        @Override
        public void run() {
            try {
                // ... diverse Layer
                System.out.println("ScopedValue : " + ServerKlasse.SCOPED_VALUE.get());
            } catch (Exception exp) {
                System.err.println("Fehler beim Zugriff :" + exp);
            }
        }
    }

}
