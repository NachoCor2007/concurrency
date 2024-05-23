package test;

import static test.ThreadUtils.*;

public class Test {
    boolean a = false, b = false; // <-- change to volatile
    int x = -1, y = -1;

    void test(int execution) {
        var t1 = new Thread(() -> {
            sleep(1);
            a = true;
            y = b ? 0 : 1;
        });
        var t2 = new Thread(() -> {
            sleep(1);
            b = true;
            x = a ? 0 : 1;
        });
        startAll(t1, t2);
        joinAll(t1, t2);

        if (x == 1 && y == 1)
            throw new RuntimeException("Failed at execution number : " + execution);
    }

    public static void main(String[] args) {
        for (var i = 0; i < 10_000; i++)
            new Test().test(i);
    }
}
