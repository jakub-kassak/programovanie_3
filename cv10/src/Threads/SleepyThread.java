package Threads;

import java.util.concurrent.TimeUnit;

public class SleepyThread implements Runnable{
    @Override
    public void run() {
        System.out.println("falling asleep");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ignored) {}
        System.out.println("waking up");
    }
}
