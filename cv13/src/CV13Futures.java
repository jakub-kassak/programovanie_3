import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CV13Futures {
//    static ExecutorService executorService = Executors.newFixedThreadPool(10);
//    static ExecutorService executorService = ForkJoinPool.commonPool();

    private static void sleep(long milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void u1() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("RESULT");

        try {
            System.out.println(cf.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void u2() {
        CompletableFuture<Void> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("starting");
            sleep(1000);
            System.out.println("done");
            return null;
        });

        System.out.println(cf2.isDone());

        while (!cf2.isDone())
            sleep(100);

        try {
            System.out.println(cf2.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void u3(){
        Random rnd = new Random();
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return rnd.nextInt();
        });
        while (!cf.isDone()){
            System.out.println("waiting...");
            sleep(100);
        }
        try {
            System.out.println(cf.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void u4(){
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() ->{
            System.out.println("first function " + Thread.currentThread());
            sleep(500);
            return "something returned";
        }).thenApply(s -> {
            System.out.println("then sleeping " + Thread.currentThread());
            sleep(500);
            System.out.println(s);
            return "then " + s;
        }).thenAccept(s -> {
            System.out.println("consuming " + Thread.currentThread());
            sleep(500);
            System.out.println(s);
        });
        CompletableFuture.runAsync(() -> {
            System.out.println("starting computing new task" + Thread.currentThread());
            sleep(500);
            System.out.println("new task computed" + Thread.currentThread());
        });

        while (!cf.isDone()){
            System.out.println("waiting " + Thread.currentThread());
            sleep(100);
        }

        try {
            System.out.println(Thread.currentThread());
            System.out.println(cf.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    static CompletableFuture<List<Integer>> createList(int bound){
        Random random = new Random();
        return CompletableFuture.supplyAsync(() ->
            IntStream.range(0, bound)
                    .map(i -> random.nextInt())
                    .peek(i -> sleep(Math.abs(i % 1000)))
                    .boxed()
                    .toList()
        );
    }

    static void doStuff(){
        System.out.println("doing stuff");
        sleep(200);
        System.out.println("done doing stuff");
    }

    static void u5(){
        Set<CompletableFuture<?>> set = ConcurrentHashMap.newKeySet();
        for (int i = 0; i < 10; i++) {
            set.add(createList(10));
        }
        doStuff();
        while (set.size() > 0){
            System.out.println("waiting");
            sleep(100);
            set.stream()
                    .filter(CompletableFuture::isDone)
                    .findFirst()
                    .map(f -> {
                            System.out.println(set.size() + " " + f.join());
                            set.remove(f);
                            return null;
                        });
        }
    }

    static CompletableFuture<Integer> secondElement(List<Integer> list){
        return CompletableFuture.supplyAsync(() -> list.get(2));
    }

    static void u6(){
        CompletableFuture<Integer> f = secondElement(List.of(1, 2, 3));
        System.out.println(f.join());
    }

    static void u7(){
        createList(5)
                .thenApply(CV13Futures::secondElement)
                .thenApply(CompletableFuture::join)
                .thenAccept(System.out::println)
                .thenRun(() -> System.out.println("done")).join();
    }

    static void u8(){
        createList(4).thenCombine(secondElement(List.of(1, 2, 3)), (u, v) -> {
            System.out.println(u);
            System.out.println(v);
            return null;
        }).join();
//        createList(4).thenCombine();
    }

    static void u9(){
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            try {
                CompletableFuture<Object> future = CompletableFuture
                        .supplyAsync(() -> {
                            if (random.nextBoolean())
                                return 1;
                            throw new RuntimeException("1");
                        }).thenApply(x -> {
                            if (random.nextBoolean())
                                return x * 2;
                            throw new RuntimeException("2");
                        }).handle((integer, throwable) -> Optional.ofNullable(integer)
                                .orElseGet(() -> {
                                    System.out.printf("Exception: %s%n", throwable.getMessage());
                                    return -1;
                                })
                        ).thenApply(j -> {
                            if (random.nextBoolean())
                                return j * 10;
                            throw new RuntimeException("3");
                        });
                try {
                    System.out.println(future.get());

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (RuntimeException ignored){}

        }

    }

    public static void main(String[] args) {
//        u1();
//        u2();
//        u3();
//        u4();
        u9();
    }
}
