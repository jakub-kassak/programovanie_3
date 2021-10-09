import dnd.D4;
import dnd.D6;

public class MyRandomDemo {

    public static void main(String[] args) {
        D6 d6 = new D6();

        for (int i = 0; i < 10; i++) {
            System.out.print(d6.nextValue() + " ");
        }
        System.out.println();


        D4 d4 = new D4();
        for (int i = 0; i < 10; i++) {
            System.out.print(d4.nextValue() + " ");
        }
        System.out.println();
    }
}
