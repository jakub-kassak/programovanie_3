package multiSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// zachovajte hlavicky triedy bez zmeny
public class MultiSearchableArrayList<T> extends ArrayList<T> {
    private static final int MAX_THREADS_NUM = 4; // alebo 4, nie je to principialne
    private static final Executor executor = Executors.newFixedThreadPool(MAX_THREADS_NUM);

    public MultiSearchableArrayList(List<T> list) {
        super(list);
    }

    public MultiSearchableArrayList() {
        super();
    }

    private boolean contains1(Object o){
        return super.parallelStream()
                .anyMatch(o::equals);
    }

    private boolean contains2(Object o){
        int size = this.size();
        final boolean[] b = {false};
        int[] split = {0, size / 4, size / 2, 3 * size / 4, size};
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            futures.add(CompletableFuture.runAsync(() -> {
                if (super.subList(split[finalI], split[finalI + 1]).contains(o))
                    b[0] = true;
            }, executor));
        }
        futures.forEach(CompletableFuture::join);
        return b[0];
    }

    private boolean contains3(Object o){
        ThreadContains[] threads = new ThreadContains[4];
        int size = this.size();
        int[] split = {0, size / 4, size / 2, 3 * size / 4, size};
        for (int i = 0; i < 4; i++) {
            threads[i] = new ThreadContains(this.subList(split[i], split[i + 1]), o);
            threads[i].start();
        }
        for (ThreadContains thread : threads) {
            try {
                thread.join();
                if (thread.contains)
                    return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public boolean contains(Object o) {
//        if (this.size() < 20){
//            return super.contains(o);
//        }
        return contains3(o);
    }

    static class ThreadContains extends Thread{
        private final List<?> list;
        private final Object o;
        private boolean contains;

        public ThreadContains(List<?> list, Object o) {
            this.list = list;
            this.o = o;
        }

        @Override
        public void run() {
            contains = list.contains(o);
        }

    }

}
