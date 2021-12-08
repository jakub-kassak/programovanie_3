package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PortScanner {

    public static void main(String[] args) {
        PortScanner ps = new PortScanner();
        System.out.println(ps.scanRange(0, 10000));
    }

    public List<Integer> scanRange(int lower, int upper){
        List<Integer> success = new ArrayList<>(upper - lower);
        for (int i = lower; i < upper; i++) {
            System.out.print(i + "\r");
            try (Socket sc = new Socket("127.0.0.1", i);
                 PrintWriter out = new PrintWriter(sc.getOutputStream());
                 BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()))) {
                System.out.println();
                success.add(i);
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return success;
    }
}
