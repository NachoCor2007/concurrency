package org.philosophers;

public class Philosopher {
    private final Table table;
    private final int leftFork;
    private final int rightFork;
    private final String name;

    public Philosopher(String name, Table table, int leftFork, int rightFork) {
        this.name= name;
        this.table = table;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    /**
     * This method simulates a philosopher eating
     * The table will be locked over the invocation of the 'grabForks' and 'releaseForks' methods
     * BUT NOT while actually eating, in this way several philosophers can be eating simultaneously
     */
    public void eat() throws InterruptedException {
        table.grabForks(leftFork, rightFork);    // <-- Once at a time
        System.out.println(name + " Eating..."); // <-- Many at a time
        Thread.sleep(100);
        table.releaseForks(leftFork, rightFork); // <-- Once at a time

    }

    public void think() throws InterruptedException {
        System.out.println(name + " Thinking...");
        Thread.sleep(100);
    }

    public void doStuff(int times) {
        try {
            for (int i = 0; i < times; i++) {
                think();
                eat();
            }
            System.out.println(name + " done!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
