import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
//import static java.lang.Math.sqrt;

public class Part2 {
    static void print(Object ...o){
        Arrays.stream(o)
                .map(Object::toString)
                .reduce((x, y) -> x + " " + y)
                .ifPresent(System.out::println);
    }

    static <I, O> Optional<O> acc(List<I> list, Function<I, O> f, BiFunction<O, O, O> g){
        return list.stream()
                .map(f)
                .reduce(g::apply);
    }

    static <I> ArrayList<I> select(List<I> list, Function<I, Boolean> f){
        return list.stream().filter(f::apply).collect(Collectors.toCollection(ArrayList::new));
    }
    
    static <In, Out> ArrayList<Out> apply(List<In> list, Function<In, Out> f){
        return list.stream()
                .map(f)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public static void main(String[] args) {
        List<Integer> list1 = IntStream.range(0, 10)
                .boxed()
                .toList();
        print("apply before:", list1);
        print("apply double:", apply(list1, x -> 2.0 * x));
        print("apply square:", apply(list1, x -> x*x));
        print("apply sqrt:", apply(list1, (Function<Integer, Double>) Math::sqrt));
        print("apply abs:", apply(list1, Math::abs));
        print("apply str:", apply(list1, Objects::toString));
        print("select:", select(list1, x -> x % 3 == 0));
        print("acc - concat squares:", acc(list1, x -> valueOf(x*x), (x, y) -> x + "-" + y));
        print("acc - sum:", acc(list1, Function.identity(), Integer::sum));
        print("acc - max:", acc(list1, Function.identity(), Integer::max));
        print(apply(list1, "*"::repeat));
        list1.stream()
                .map("*"::repeat)
                .forEach(System.out::println);
        int inc = 10;
        print("apply - inc", apply(list1, x -> x + inc));

    }
}
