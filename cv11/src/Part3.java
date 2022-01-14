import java.util.*;
import java.util.function.BiFunction;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Part3 {

    static void print(Object...o){
        Arrays.stream(o)
                .map(Object::toString)
                .reduce((x, y) -> x + " " + y)
                .ifPresent(System.out::println);
    }

    static <I> ArrayList<I> select(List<I> list, Predicate<I> f){
        return list.stream()
                .filter(f)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    static <In, Out> ArrayList<Out> apply(List<In> list, Function<In, Out> f){
        return list.stream()
                .map(f)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    static Double mySqrt(double x){
        if (x < 0)
            return null;
        return Math.sqrt(x);
    }

    static void stringOps(){
        List<String> a = List.of("abaaabab", "", "aabbaba", "abbabba", "", "bb", "aa", "ab");
        print("select empty:", select(a, String::isEmpty));
        print("select not empty:", select(a, Predicate.not(String::isEmpty)));
        Predicate<String> notEmty = Predicate.not(String::isEmpty);
        Predicate<String> startWithA = notEmty.and(s -> s.charAt(0) == 'a');
        Predicate<String> endsWithB = notEmty.and(s -> s.charAt(s.length() - 1) == 'b');
        print("select start with a:", select(a, startWithA));
        print("select ends with b:", select(a, endsWithB));
        print("select starts with a and ends with b:", select(a, endsWithB.and(startWithA)));
        List<String> b = a.stream()
                .map(s -> s.replaceAll("a", "x"))
                .toList();
        print("replacement a->b:", b);
    }
    
    record Number(Double val) {}
    
    static int counter = 3;
    static Number createNumber(Double val){
        if (counter++ > 2)
            return new Number(val);
        return null;
    }

    static Optional<Double> val(Number n){
        return Optional.ofNullable(n.val());
    }

    static void doubleOps(){
        List<Double> a = DoubleStream.generate(new DoubleSupplier() {
            double a = 3.4235;

            @Override
            public double getAsDouble() {
                a = (a * 4235.345) % 10_000 + 231.4872;
                return a;
            }
        }).limit(20).boxed().toList();
        List<Double> b = List.of(1.5, 1.6, 3452.33245,  -3241.0, -423.23, 1.0, 0.0);
        print("a:", a);
        print("mySqrt:", apply(apply(a, Part3::mySqrt), Optional::ofNullable));
        print("b:", b);
        print("pokus 1");
        b.stream()
                .map(Part3::mySqrt)
                .map(Part3::createNumber)
                .map(Optional::ofNullable)
                .map(z -> z.flatMap(Part3::val).orElse(0.))
                .reduce(Double::sum)
                .ifPresent(Part3::print);
        print("pokus 2");
        b.stream()
                .map(Part3::mySqrt)
                .map(Optional::ofNullable)
                .map(z -> z.orElse(0.))
                .reduce(Double::sum)
                .ifPresent(Part3::print);
        b.stream()
                .map(Part3::mySqrt)
                .filter(Objects::nonNull)
                .reduce(Double::sum)
                .ifPresent(Part3::print);
        b.stream()
                .map(Part3::mySqrt)
                .flatMap(Stream::ofNullable)
                .reduce(Double::sum)
                .ifPresent(Part3::print);
        Optional<Integer> o = Optional.empty();
        o.map(Part3::mySqrt)
                .map(Part3::mySqrt)
                .map(Part3::mySqrt)
                .ifPresentOrElse(System.out::println, () -> System.out.println("nespracovatelny vstup"));
        Stream<Optional<Integer>> s = Stream.of(o);

    }

    public static void main(String[] args) {
        stringOps();
        doubleOps();
    }
}
