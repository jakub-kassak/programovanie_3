package DBNormalForms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Algorithm {

    public static Set<String> attributesClosure(Set<String> attrs, Set<? extends FunctionalDependency> dependencies){
        Set<String> r = new HashSet<>(attrs);
        Set<String> r2 = new HashSet<>(attrs);
        Set<FunctionalDependency> f = ConcurrentHashMap.newKeySet();
        f.addAll(dependencies);
        while (f.size() > 0){
            for (FunctionalDependency d : f){
                if (r2.containsAll(d.left())){
                    r2.addAll(d.right());
                    f.remove(d);
                }
            }
            if (r2.equals(r))
                break;
            r.addAll(r2);
        }
        return r;
    }

    static void print(Set<? extends FunctionalDependency> set, String msg){
        System.out.println(msg);
        set.stream()
                .map(Object::toString)
                .map(s -> "\t" + s)
                .sorted()
                .forEach(System.out::println);

    }

    static boolean coversDependency(Set<? extends FunctionalDependency> set, FunctionalDependency fd){
        return attributesClosure(fd.left(), set).containsAll(fd.right());
    }

    public static Set<CanonicalDependency> minimalCover(Set<FunctionalDependency> dependencies){
//        print(dependencies, "start");

        Set<CanonicalDependency> f1 = new HashSet<>();
        //step 1 - break right sides
        for (FunctionalDependency fd : dependencies){
            f1.addAll(CanonicalDependency.canonize(fd));
        }

//        print(f1, "step 1");

        //step 2 - shorten left sides - remove redu
        Set<CanonicalDependency> f2 = new HashSet<>(f1);
        for (CanonicalDependency X_A : f1){
            Set<String> X = new HashSet<>(X_A.left());
            String A = X_A.rightAttr();
            for (String Xi : X_A.left()) {
                X.remove(Xi);
                if (attributesClosure(X, f2).contains(A)) {
                    f2.remove(X_A);
                    X_A = new CanonicalDependency(X, A);
                    f2.add(X_A);
                } else {
                    X.add(Xi);
                }
            }
        }

//        print(f2, "step 2: ");

        //step 3 - remove redundant dependencies
        Set<CanonicalDependency> f3 = new HashSet<>(f2);
        for (CanonicalDependency cd : f2){
            f3.remove(cd);
            if (!coversDependency(f3, cd))
                f3.add(cd);
        }

//        print(f3, "step 3:");

        return f3;
    }

    public static Schema normalizeTo3NF(Set<String> attrs, Set<CanonicalDependency> dependencies){
        Set<Schema> schemas = new HashSet<>();
        HashMap<Set<String>, Set<String>> map = new HashMap<>();
        for (CanonicalDependency cd : dependencies){
            map.compute(cd.left(), (k, v) -> {
                if (v == null)
                    return cd.right();
                Set<String> set2 = new HashSet<>(cd.right());
                set2.addAll(v);
                return set2;
            });
        }
        Set<FunctionalDependency> set = map.entrySet()
                .stream()
                .map(e -> new SimpleDependency(e.getKey(), e.getValue()))
                .collect(Collectors.toSet());
        for (FunctionalDependency fd : set){
            Set<String> a2 = new HashSet<>(fd.left());
            a2.addAll(fd.right());
            schemas.add(new SimpleSchema(a2, set));
        }
        schemas.stream()
                .map(schema -> schema.relations() + " :: " + schema.dependencies())
                .forEach(System.out::println);
        return new ComplexSchema(schemas);
    }

    public static void main(String[] args) {
        Set<String> attrs = Set.of("A", "C", "D", "E");
        Set<FunctionalDependency> dependencies = Set.of(
                new SimpleDependency("AB->C"),
                new SimpleDependency("ACD->B"),
                new SimpleDependency("CG->BD"),
                new SimpleDependency("C->A"),
                new SimpleDependency("D->EG"),
                new SimpleDependency("CE->AG"),
                new SimpleDependency("BC->D"),
                new SimpleDependency("BE->C")
        );
        Schema schema = new SimpleSchema(attrs, dependencies);
        System.out.println(schema);
//        System.out.println(dependencies);
//        System.out.println(attributesClosure(attrs, dependencies));
//        System.out.println(CanonicalDependency.canonize(new SimpleDependency("ABC->AXS")));
        System.out.println(attributesClosure(Set.of("A"), dependencies));
        System.out.println(attributesClosure(Set.of("B"), dependencies));
        System.out.println(attributesClosure(Set.of("C"), dependencies));
        System.out.println(attributesClosure(Set.of("D"), dependencies));
        System.out.println(minimalCover(dependencies));

        System.out.println(normalizeTo3NF(attrs, minimalCover(dependencies)));
    }
}
