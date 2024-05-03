package org.philosophers;

public class Table {
    private final boolean[] forks = new boolean[5];

    /**
     * Check if BOTH needed forks are available (false)
     * If any of them is 'in use' (true) invoke wait()
     * and try again when notified from other thread
     * When both forks are available, mark them as 'in use' (true)
     * <p>
     * NOTE: because this is a monitor when 'wait' is invoked the lock over the table
     * is released and other threads can work over the table.
     * When the monitor is notified the lock will be reacquired before returning from 'wait'
     */
    public synchronized void grabForks(int leftFork, int rightFork) throws InterruptedException {
        while (forks[leftFork] || forks[rightFork])
            wait();
        forks[leftFork] = true;
        forks[rightFork] = true;
    }

    /**
     * Mark my 2 forks as available.
     * notify the table object so all threads waiting in the 'grabForks' method
     * will be waked up.
     */
    public synchronized void releaseForks(int leftFork, int rightFork) {
        forks[leftFork] = false;
        forks[rightFork] = false;
        notifyAll();
    }
}
