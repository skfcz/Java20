package de.groygroy.java20;

import java.util.NoSuchElementException;

/**
 * Beispiel für Scoped Value, JEP 429
 */
public class ScopedValue {
    public final static jdk.incubator.concurrent.ScopedValue<String> SCOPED_VALUE = jdk.incubator.concurrent.ScopedValue.newInstance();

    public static void main(String... args) {

        System.out.println("Zugriff mit Scope");
        for (int i = 0; i < 5; i++) {
            // Variable im neuen Scope setzen und Code starten
            final String wert = "#" + i;
            jdk.incubator.concurrent.ScopedValue.where(SCOPED_VALUE, wert).run(new Aufgabe());
        }
        System.out.println("Zugriff ohne Scope");
        new Thread(new Aufgabe()).start();
    }

    static class Aufgabe implements Runnable {
        @Override
        public void run() {
            try {
                // ... diverse Layer
                System.out.println("ScopedValue : " + ScopedValue.SCOPED_VALUE.get());
            } catch (Exception exp) {
                System.err.println("Fehler beim Zugriff :" + exp);
            }
        }
    }

}
