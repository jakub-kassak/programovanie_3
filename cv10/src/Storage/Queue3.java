package Storage;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue3 implements Storage{
    private final LinkedList<Integer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private boolean isFull = false;
    private boolean isEmpty = true;
    private final Condition condVar = lock.newCondition();


    @Override
    public void push(int x) {
        lock.lock();
        while (isFull){
            System.out.println("push wait");
            try {
                condVar.await();
            } catch (InterruptedException ignored) {}
        }
        System.out.println("push");
        queue.push(x);
        isFull = queue.size() == 3;
        isEmpty = false;
        condVar.signal();
        lock.unlock();
    }

    @Override
    public Integer pop() {
        lock.lock();
        while (isEmpty){
            System.out.println("wait pop");
            try {
                condVar.await();
            } catch (InterruptedException ignored) {}
        }
        System.out.println("pop");
        int ret = queue.pop();
        isEmpty = queue.size() == 0;
        isFull = false;
        condVar.signal();
        lock.unlock();
        return ret;
    }
}
