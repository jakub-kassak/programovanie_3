package Threads;

public class SelfishThread extends Thread{

    public SelfishThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (i % 100_000_000 == 0)
                System.out.printf("%s >> %d%n", getName(), i / 100_000_000);
        }
    }
}
