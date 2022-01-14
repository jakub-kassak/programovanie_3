import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    static Stream<Integer> s(){
        return IntStream.range(1, 16).boxed();
    }

    static void print(List<Object> l){
        l.stream()
                .map(x -> x instanceof Stream ? ((Stream<?>) x).toList() : x)
                .map(Object::toString)
                .forEach(System.out::println);
    }

    static void part5(){
        int inc = 10;
        print(List.of(
                "stream s",
                s(),
                "Vytvorte List, obsahujúci dvojnásobky prvkov z prúdu S.",
                s().map(x -> 2*x),
                "Vytvorte List, obsahujúci štvorce prvkov z prúdu S.",
                s().map(x -> x*x),
                "Vytvorte List, obsahujúci odmocniny prvkov z prúdu S.",
                s().map(Math::sqrt)
                   .map("%.2f"::formatted),
                "Vytvorte List, obsahujúci prvky z prúdu S, ktoré sú väčšie ako 2.",
                s().filter(x -> x > 2),
                "Vytvorte List, obsahujúci párne prvky z prúdu S.",
                s().filter(x -> x % 2 == 0),
                "Vytvorte List, obsahujúci nepárne násobky trojky z prúdu S.",
                s().filter(x -> x % 3 == 0)
                   .filter(x -> x % 2 == 1),
                "Pre každý prvok X prúdu S vypíšte na samostatný riadok X hviezdičiek.",
                s().map("*"::repeat).collect(Collectors.joining("\n")),
                "Vypíšte súčet prvkov prúdu S (pomocou štandardnej funkcie `sum`).",
                s().mapToInt(x -> x).sum(),
                s().reduce(0, Integer::sum),
                "Vypíšte maximum prvkov prúdu S pomocou metódy reduce. Spravte aj pre jednoargumentovú aj pre dvojargumentovú verziu príkazu reduce",
                s().max(Comparator.naturalOrder()),
                s().reduce(Integer::max).orElse(Integer.MIN_VALUE),
                s().reduce(Integer.MIN_VALUE, Integer::max),
                "Vytvorte premennú inc s nejakou hodnotou. Vytvorte List, obsahujúci prvky prúdu S zvýšené o hodnotu premennej inc.",
                s().map(x -> x += inc)
        ));
    }

    static void part6(){
        print(List.of(
                "Napíšte na jeden riadok príkaz, ktorý vytvorí List, obsahujúci práve všetky prvočísla menšie ako 1000.",
                IntStream.range(2, 1000)
                        .filter(Streams::isPrime)
                        .boxed(),
                "Napíšte na jeden riadok príkaz, ktorý vytvorí List, obsahujúci práve 200 najmenších prvočísel.",
                Stream.iterate(2, i -> ++i)
                        .filter(Streams::isPrime)
                        .limit(200),
                "Napíšte na jeden riadok príkaz, ktorý vytvorí List, obsahujúci práve 200 najmenších prirodzených čísel, ",
                "pre ktoré Collatzova postupnosť neobsahuje hodnotu 1 po 100 krokoch (Collatzova postupnosť pre číslo n vyzerá nasledovne: prvý prvok je n, ",
                "každý nasledujúci prvok je polovica predchádzajúceho, ak bol párny, a trojnásobok plus 1, ak nebol párny. ",
                "Otázka, či pre každé prirodzené číslo jeho Collatzova postupnosť dosahuje 1, je jedným z najznámejších otvorených problémov matematiky).",
                Stream.iterate(0, i -> ++i)
                        .parallel()
                        .filter(n -> Stream.iterate(n, i -> i % 2 == 0 ? i /2 : i * 3 + 1)
                                    .limit(10000000)
                                    .noneMatch(i -> i == 1))
                        .limit(200)

        ));

    }

    static boolean isPrime(int n){
        return IntStream.range(2, (int) Math.sqrt(n) + 1).noneMatch(i -> n % i == 0);
    }

    public static void main(String[] args) {
        part5();
        part6();
    }
}
