import Storage.*;

public class StorageDemo {
    public static void main(String[] args) {
        Storage storage = new Queue3();
        Producer producer1 = new Producer(storage);
        Consumer consumer1 = new Consumer(storage);
        Producer producer2 = new Producer(storage);
        Consumer consumer2 = new Consumer(storage);

        consumer1.start();
        producer1.start();
        producer2.start();
        consumer2.start();
    }
}
