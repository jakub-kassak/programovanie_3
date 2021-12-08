package Storage;

import java.util.ArrayList;
import java.util.LinkedList;

public class SynchronizedStorage implements Storage{
    private final LinkedList<Integer> list = new LinkedList<>();


    @Override
    public synchronized void push(int x) {
        list.push(x);
        notifyAll();
    }

    @Override
    public synchronized Integer pop() {
        while (list.size() == 0){
            try{
                wait();
            } catch (InterruptedException ignored) {}
        }
        return list.pop();
    }
}
