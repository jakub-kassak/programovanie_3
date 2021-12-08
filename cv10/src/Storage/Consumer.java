package Storage;

public class Consumer extends Thread {
    private final Storage storage;


    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i <5 ; i++) {
            System.out.println(storage.pop());
        }
    }
}
