package test;

import java.util.ArrayList;

public class ThreadUtils {
    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startAll(ArrayList<Thread> threads) {
        for (Thread thread : threads) { thread.start(); }
    }
    public static void joinAll(ArrayList<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
