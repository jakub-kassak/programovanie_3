import dnd.Dn;
import dnd.ICG;
import dnd.LCG;
import dnd.NDie;

import static dnd.DieTester.testConsecutivePairs;
import static dnd.DieTester.testDistribution;

public class MyRandomDemo {

    public static void main(String[] args) {
        /*
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
        */

        NDie die = new Dn(6);
        die.setRndStrategy(new LCG(65539, 0,  2_147_483_648L, 47)); //RANDU settings
        //die.setRndStrategy(new LCG(1103515245, 12345,  2_147_483_648L, 47)); //GLIBC settings
        //die.setRndStrategy(new ICG(849, 1, 1031, 47));
        //die.setRndStrategy(new ICG(858993221, 1, 2147483053, 47));
        int tries = 100;
        for (int i = 0; i < tries; i++) {
            System.out.print(die.nextValue() + " ");
        }
        System.out.println();
        testDistribution(die, tries);
        testConsecutivePairs(die, tries);


    }
}
