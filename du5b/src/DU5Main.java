import FSBT.FBST;
import FSBT.U1;

import java.util.*;

public class DU5Main {


    private static void print(FBST f, String s){
        if (f == null)
            return;
        print(f.left(), s + "  |");
        System.out.println(s + "==" + f.value());
        print(f.right(), s + "  |");
    }

    static void print(Object...l){
        Arrays.stream(l)
                .map(Object::toString)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        FBST fbst1 = new FBST(0,
                new FBST(-1, null, null),
                new FBST(3,
                        new FBST(2, null, null),
                        null));
        FBST fbst2 = new FBST(25,
                new FBST(20, null, null),
                new FBST(40,
                        new FBST(30, null, null),
                        null));
        FBST fbst3 = new FBST(4,
                fbst1,
                fbst2);
        print(fbst3, "");
        print("____________________");

        print(U1.add.apply(fbst3).apply(21), "");
        print("____________________");
        print(U1.popMin.apply(fbst3), "");
        print("____________________");
        print(U1.add.apply(null).apply(1), "");
//        print(FSBT.U1.add.apply(FSBT.U1.add.apply(null).apply(1)).apply(1), "");
//        print(FSBT.U1.add.apply(null).apply(-1), "");
//        print(FSBT.U1.popMin.andThen(FSBT.U1.popMin).apply(fbst3), "");
//        print("____________________");
//        print(FSBT.U1.popMin.andThen(FSBT.U1.popMin).andThen(FSBT.U1.popMin).andThen(FSBT.U1.popMin).andThen(FSBT.U1.popMin).apply(fbst3), "");
//        print("____________________");
        print(
                "size fbst3: " + U1.size.apply(fbst3),
                "contains 21: " + U1.contains.apply(fbst3).apply(21)
        );
        test();
    }

    static Random random = new Random();
    static TreeSet<Integer> set = new TreeSet<>();
    static FBST fbst = null;
    static void test(){


        int testCount = 50000;
        for (int i = 1; i < testCount; i++) {
            int op = random.nextInt(8);
            switch (op){
                case 0, 1, 2, 3 -> addOp(testCount);
                case 4 -> sizeOp();
                case 5 -> containsOP(testCount);
                case 6 -> popMinOp();
                case 7 -> removeOp(testCount);
            }
        }
        print(fbst, "");
        print(U1.size.apply(fbst), set.size());
    }

    static void addOp(int modulo){
        int rInt = random.nextInt() % modulo;
        set.add(rInt);
        fbst =  U1.add.apply(fbst).apply(rInt);
    }

    static void sizeOp(){
        assert set.size() == U1.size.apply(fbst) : "size is different";
    }

    static void containsOP(int modulo){
        int tInt = random.nextInt() % modulo;
        assert set.contains(tInt) == U1.contains.apply(fbst).apply(tInt) : "not eqal contains";
    }

    static void popMinOp(){
        Integer min = set.pollFirst();
        assert Objects.equals(min, U1.min.apply(fbst)) : "not equal min";
        if (min != null) {
            set.remove(min);
            fbst = U1.popMin.apply(fbst);
            assert set.contains(min) == U1.contains.apply(fbst).apply(min) : "popMin is not working";
        }
    }

    static void removeOp(int modulo){
        int rInt = random.nextInt() % modulo;
        set.remove(rInt);
        fbst = U1.remove.apply(fbst).apply(rInt);
        assert set.contains(rInt) == U1.contains.apply(fbst).apply(rInt) : "remove is not working";
    }
}
