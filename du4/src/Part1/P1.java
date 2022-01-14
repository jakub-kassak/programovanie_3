package Part1;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface P1 {
        Function<FL, Boolean> isEmpty = Objects::isNull;


        Function<FL, Integer> size = fl ->
                isEmpty.apply(fl) ? 0 : 1 + P1.size.apply(fl.tail());


        Function<FL, Integer> min = fl ->
                isEmpty.apply(fl) ? null : Math.min(
                        Optional.ofNullable(P1.min.apply(fl.tail()))
                                .orElse(Integer.MAX_VALUE),
                        fl.head()
                );


        Function<FL, Function<Integer, FL>> push_back = fl -> i ->
                P1.isEmpty.apply(fl)
                        ?  new FL(i, null)
                        :  new FL(
                                fl.head(),
                                P1.push_back.apply(fl.tail()).apply(i)
                );

        BiFunction<FL, Integer, FL> push_back2 = (fl, i) ->
                P1.isEmpty.apply(fl)
                        ? new FL(i, null)
                        : new FL(fl.head(),
                                 P1.push_back2.apply(fl.tail(), i));

        Function<FL, FL> pop_back = fl ->
                size.apply(fl) <= 1 ? null : new FL(fl.head(), P1.pop_back.apply(fl.tail()));


        Function<FL, FL> reverse = fl ->
                P1.isEmpty.apply(fl)
                        ? fl
                        : P1.push_back.apply(P1.reverse.apply(fl.tail())).apply(fl.head());


        Function<FL, Function<FL, FL>> concat = fl1 -> fl2 ->
                P1.isEmpty.apply(fl1)
                        ? fl2
                        : new FL(fl1.head(), P1.concat.apply(fl1.tail()).apply(fl2));


        Function<FL, Function<Integer, Boolean>> contains = fl -> n ->
                !P1.isEmpty.apply(fl) && (Objects.equals(n, fl.head()) || P1.contains.apply(fl.tail()).apply(n));


        Function<FL, Function<Integer, FL>> insert = fl -> n ->
                P1.isEmpty.apply(fl) || fl.head() >= n
                        ? new FL(n, fl)
                        : new FL(fl.head(), P1.insert.apply(fl.tail()).apply(n));

        Function<FL, FL> sort = fl ->
                P1.isEmpty.apply(fl)
                        ? null
                        : P1.insert.apply(P1.sort.apply(fl.tail())).apply(fl.head());

        BiFunction<FL, Predicate<Integer>, FL> filter = (fl, p) ->
                P1.isEmpty.apply(fl)
                        ? null
                        : p.test(fl.head())
                        ? new FL(fl.head(), P1.filter.apply(fl.tail(), p))
                        : P1.filter.apply(fl.tail(), p);

        Function<FL, FL> sort2 = fl ->
                P1.size.apply(fl) <= 1
                        ? fl
                        : P1.concat.apply(
                                        P1.sort2.apply(P1.filter.apply(fl.tail(), x -> x <= fl.head())))
                                .apply(
                                        new FL(fl.head(), P1.sort2.apply(P1.filter.apply(fl.tail(), x -> x > fl.head()))));

}
