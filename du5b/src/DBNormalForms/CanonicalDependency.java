package DBNormalForms;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CanonicalDependency extends SimpleDependency{
    public CanonicalDependency(Set<String> left, Set<String> right) {
        super(left, right);
    }

    public CanonicalDependency(String s) {
        super(s);
        if (right().size() != 1)
            throw new ArithmeticException();
    }

    public CanonicalDependency(Set<String> left, String right){
        super(left, Set.of(right));
    }

    public String rightAttr(){
        return right().stream().findAny().orElse(null);
    }

    public static Set<CanonicalDependency> canonize(FunctionalDependency X_Y){
        Set<String> X = X_Y.left();
        return X_Y.right().stream()
                .filter(A -> !X.contains(A))
                .map(A -> new CanonicalDependency(X, A))
                .collect(Collectors.toSet());

    }
}
