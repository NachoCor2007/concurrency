package test;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

import static test.ThreadUtils.joinAll;
import static test.ThreadUtils.startAll;

public class Test {
    public static void main(String[] args) {
        var barrier = new Barrier(5);
        var threads = new ArrayList<Thread>();

        for (int i = 0; i < 5; i++) {
            var t1 = new Thread(() -> {
                System.out.println("Wait for the Barrier");
                barrier.await();
                System.out.println("Pass the Barrier");
            });
            threads.add(t1);
        }

        startAll(threads);
        joinAll(threads);
    }

}
