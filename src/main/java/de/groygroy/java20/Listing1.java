package de.groygroy.java20;

import java.util.Set;

public class Listing1 {


    record Punkt(double x, double y) {
    }

    record BoundingBox(Punkt min, Punkt max) {
    }



    public static void main(String... args) {

        Set<Object> objects = Set.of(
                new BoundingBox(new Punkt(-1, -1), new Punkt(10, 20)),
                new BoundingBox(new Punkt(-2, -2), new Punkt(4, 5)),
                new Punkt(42, 1));

        for (Object obj : objects) {
            System.out.println("\ntest " + obj);

            // JEP 432: Record Pattern
            if (obj instanceof Punkt p) {
                System.out.println("    instanceof Punkt x/y: " + p.x + ", " + p.y);
            } else if (obj instanceof BoundingBox(var m, Punkt(var x, var y))) {
                System.out.println("    instanceof BBox min. Punkt " + m);
                System.out.println("         max. " + x + ", " + y);
            }

            // JEP 433: Pattern Matching for switch
            switch (obj) {
                case BoundingBox(var a, var b)when b.x > 5 -> System.out.println("    switch a " + b);
                case BoundingBox bbox -> System.out.println("    switch b " + bbox.max);
                default -> System.out.println("    switch sonstiges");
            }
        }

        Set<Punkt> punkte = Set.of( new Punkt(0,0), new Punkt(2,3));
        for (Punkt(double x, double y) : punkte) {
            System.out.printf("Punkt %f %f %n", x, y);
        }


    }
}
