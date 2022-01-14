import Part1.FL;
import Part1.P1;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class DU4Main {
    static Random random = new Random();

    static void print(List<Object> l){
        l.stream()
                .map(Object::toString)
                .forEach(System.out::println);
    }

    static FL randomFL(){
        return IntStream.range(0, 20)
                .map(x -> random.nextInt() % 100)
                .mapToObj(i -> new FL(i, null))
                .reduce((f1, f2) -> P1.concat.apply(f1).apply(f2))
                .orElse(null);
    }

    public static void main(String[] args) {
        FL fl = new FL(0, new FL(1, new FL(2, null)));
        FL fl2 = new FL(10, new FL(11, new FL(12, null)));
        print(List.of(
                "fl: " + fl,
                "fl2: " + fl2,
                "is empty fl: " + P1.isEmpty.apply(fl),
                "is empty null: " + P1.isEmpty.apply(null),

                "fl size: " + P1.size.apply(fl),
                "null size: " + P1.size.apply(null),

                "pop back fl: " + P1.pop_back.apply(fl),
                "pop back 2x fl: " + P1.pop_back.apply(P1.pop_back.apply(fl)),
                "min fl: " + P1.min.apply(fl),
                "min null: " + P1.min.apply(null),
                "push back 5: " + P1.push_back.apply(fl).apply(5),
                "push_back2 5: " + P1.push_back2.apply(fl, 5),
                "pop back: " + P1.pop_back.apply(fl),
                "pop back 2x: " + P1.pop_back.andThen(P1.pop_back).apply(fl),
                "pop back 3x: " + P1.pop_back.andThen(P1.pop_back).andThen(P1.pop_back).apply(fl),
                "pop back null: " + P1.pop_back.apply(null),
                "reverse fl: " + P1.reverse.apply(fl),
                "concat fl + fl2: " + P1.concat.apply(fl).apply(fl2),
                "concat fl2 + fl: " + P1.concat.apply(fl2).apply(fl),
                "fl contains 1: " + P1.contains.apply(fl).apply(1),
                "fl2 contains 1: " + P1.contains.apply(fl2).apply(1),
                "insert 1 into fl: " + P1.insert.apply(fl).apply(1),
                "insert 5 into concat fl + fl2: " + P1.insert.apply(P1.concat.apply(fl).apply(fl2)).apply(5),
                "sort concat fl2 + fl: " + P1.sort.apply(P1.concat.apply(fl2).apply(fl)),
                "filter concat fl2 + fl: " + P1.filter.apply(P1.concat.apply(fl2).apply(fl), i -> i % 2 == 0),
                "sort2 random: " + P1.sort2.apply(randomFL())
        ));
    }
}
