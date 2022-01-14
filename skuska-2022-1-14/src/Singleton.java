public class Singleton {
    private static volatile Singleton instance = null;

    private Singleton(){}

    public static Singleton create() {
        Singleton local = instance;
        if (local == null){
            synchronized (Singleton.class){
                local = instance;
                if (local == null) {
                    instance = local = new Singleton();
                }
            }
        }
        return local;
    }

//    record Pair (Integer i, Integer ii){};
//    public static void main(String[] args) {
//        IntStream.range(1, 10)
//                .boxed()
//                .reduce(new Pair(0, 0), new BiFunction<Pair, Integer, Pair>() {
//                    @Override
//                    public Pair apply(Pair pair, Integer integer) {
//                        return new Pair(pair.i + 1, pair.ii + integer);
//                    }
//                });
//    }
}
