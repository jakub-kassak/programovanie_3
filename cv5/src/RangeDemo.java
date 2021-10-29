public class RangeDemo {

    public static void main(String[] args) {
        for(Integer i : new Range(10,-8, -3)){
            System.out.println(i);
        }
        for(Integer i : new InfiniteRange(Integer.MAX_VALUE - 10, 3)){
            System.out.println(i);
        }
    }
}
