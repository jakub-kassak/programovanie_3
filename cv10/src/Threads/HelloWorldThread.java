package Threads;

public class HelloWorldThread extends Thread{

    @Override
    public void run() {
        System.out.println("Hello");
    }
}
