package MarsColony;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Printer implements Runnable{
    ArrayList<Object> registered = new ArrayList<>();

    @Override
    public void run() {
        int i = 0;
        while (true){
            System.out.println("Time elapsed: " + i++ + "s");
            registered.forEach(System.out::println);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerObject(Object o){
        registered.add(o);
    }
}
