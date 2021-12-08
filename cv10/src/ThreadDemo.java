import Threads.HelloWorldThread;
import Threads.SelfishThread;
import Threads.SleepyThread;

import java.util.ArrayList;

public class ThreadDemo {

    public static void printThings(Thread thread){
        System.out.println(thread.getPriority());
        System.out.println(thread.getState());
    }

    public static void main(String[] args) {

        SleepyThread st = new SleepyThread();
        Thread thread = new Thread(st);
        thread.start();

        HelloWorldThread hwt = new HelloWorldThread();
        printThings(hwt);
        hwt.start();
        printThings(hwt);
        try {
            hwt.join();
        } catch (InterruptedException ignored){}
        printThings(hwt);

        ArrayList<SelfishThread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new SelfishThread(String.valueOf(i)));
        }
        for (int i = 0; i < 10; i++) {
            list.get(i).start();
        }
        System.out.println("stred");
        for (int i = 0; i < 10; i++) {
            try {
                list.get(i).join();
            } catch (InterruptedException ignored) {}
        }
        System.out.println("koniec");


    }
}
