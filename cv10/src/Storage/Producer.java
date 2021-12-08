package Storage;

import java.util.concurrent.TimeUnit;

public class Producer extends Thread{
    private final Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ignored) {}
            storage.push(i);
        }
    }
}
