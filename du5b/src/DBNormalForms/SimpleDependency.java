package DBNormalForms;

import java.util.*;

public class SimpleDependency implements FunctionalDependency {
    private final Set<String> left;
    private final Set<String> right;

    public SimpleDependency(Set<String> left, Set<String> right) {
        this.left = new HashSet<>(left);
        this.right = new HashSet<>(right);
    }

    public SimpleDependency(String s){
        this(Set.of(), Set.of());
        if (!s.contains("->"))
            throw new ArithmeticException();
        boolean l = true;
        for (char c : s.replaceAll("\\s", "").toCharArray()){
            if (c == '-' || c == '>')
                l = false;
            else if (l)
                left.add(String.valueOf(c).toUpperCase(Locale.ROOT));
            else
                right.add(String.valueOf(c).toUpperCase(Locale.ROOT));
        }
    }

    @Override
    public Set<String> left() {
        return Collections.unmodifiableSet(left);
    }

    @Override
    public Set<String> right() {
        return Collections.unmodifiableSet(right);
    }

    @Override
    public String toString() {
        return left + " -> " + right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleDependency that = (SimpleDependency) o;
        return left.equals(that.left) && right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
