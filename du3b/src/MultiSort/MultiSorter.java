package MultiSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MultiSorter {
    private final int max_depth;

    public MultiSorter(int max_depth) {
        this.max_depth = max_depth;
    }

    private void sort1(ArrayList<Integer> array){
        ArrayList<Integer> a = array.parallelStream().sorted().collect(Collectors.toCollection(ArrayList::new));
        array.clear();
        array.addAll(a);
    }

    private Void sort3(ArrayList<Integer> array, int depth){
//        System.out.println(depth);
        if (depth >= 0) {
            Collections.sort(array);
        }else {
            int size = array.size();
            ArrayList<Integer> sub1 = new ArrayList<>(array.subList(0, size / 2));
            ArrayList<Integer> sub2 = new ArrayList<>(array.subList(size / 2, size));
            sort3(sub1, depth + 1);
            CompletableFuture<Void> f = CompletableFuture.supplyAsync(() ->
                    sort3(sub2, depth + 1));
            f.join();
            merge(array, sub1, sub2);
        }
        return null;
    }

    private void sort2(ArrayList<Integer> array, int depth) {
        int size = array.size();
        ArrayList<Integer> sub1 = new ArrayList<>(array.subList(0, size / 2));
        ArrayList<Integer> sub2 = new ArrayList<>(array.subList(size / 2, size));
        if (depth < 0 ){
            sort2(sub1, depth + 1);
            sort2(sub2, depth + 1);
        } else {
            sort2Async(sub1, sub2);
        }
        merge(array, sub1, sub2);
    }

    private void sort2Async(ArrayList<Integer> sub1, ArrayList<Integer> sub2) {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> Collections.sort(sub1));
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> Collections.sort(sub2));
        f1.join();
        f2.join();
    }

    private void merge(List<Integer> array, List<Integer> sub1, List<Integer> sub2){
        int size1 = sub1.size();
        int size2 = sub2.size();
        int i1 = 0;
        int i2 = 0;
        array.clear();
        while (i1 < size1 && i2 < size2){
            if (sub1.get(i1) < sub2.get(i2)) {
                array.add(sub1.get(i1++));
            }else {
                array.add(sub2.get(i2++));
            }
        }
        array.addAll(sub1.subList(i1, size1));
        array.addAll(sub2.subList(i2, size2));
    }

    public void sort(ArrayList<Integer> array) {
        sort1(array);
//        sort2(array, -2);
    }

//    private void sort(List<Integer> array, int depth){
//        if (depth > max_depth){
//            Collections.sort(array);
//        }else if (array.size() > 1) {
//            class ThreadSorter extends Thread{
//                final List<Integer> list;
//
//                public ThreadSorter(List<Integer> array) {
//                    this.list = array;
//                }
//
//                @Override
//                public void run() {
//                    sort(list, depth + 1);
//                }
//            }
//            int middle = array.size() / 2;
//            ThreadSorter t1 = new ThreadSorter(array.subList(0, middle));
//            ThreadSorter t2 = new ThreadSorter(array.subList(middle, array.size()));
//            t1.start();
//            t2.start();
//            try {
//                t1.join();
//                t2.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            List<Integer> sorted = merge(t1.list, t2.list);
//            for (int i = 0; i < sorted.size(); i++) {
//                array.set(i, sorted.get(i));
//            }
//            ArrayList<Integer> control = new ArrayList<>(array);
//            Collections.sort(control);
//            if (!control.equals(array))
//                System.out.println("zle");
//        }
//    }
//
//    private ArrayList<Integer> merge(List<Integer> left, List<Integer> right){
//        ArrayList<Integer> sorted = new ArrayList<>();
//        int l = 0;
//        int r = 0;
//        while (l < left.size() && r < right.size()) {
//            if (left.get(l) < right.get(r)){
//                sorted.add(left.get(l++));
//            }else {
//                sorted.add(right.get(r++));
//            }
//        }
//        while (r < right.size())
//            sorted.add(right.get(r++));
//        while (l < left.size())
//            sorted.add(right.get(l++));
//        return sorted;
//    }
}
