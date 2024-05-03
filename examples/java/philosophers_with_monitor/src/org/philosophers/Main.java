package org.philosophers;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var names = new String[]{"Plato", "Aristotle", "Aquinas", "Descartes", "Kant"};
        var table = new Table();
        var philosophers = new ArrayList<Philosopher>();
        var threads = new ArrayList<Thread>();

        for (int i = 0; i < 5; i++)
            philosophers.add(new Philosopher(names[i], table, i, (i + 1) % 5));

        for (var philosopher : philosophers)
            threads.add(new Thread(() -> philosopher.doStuff(10)));

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }

}