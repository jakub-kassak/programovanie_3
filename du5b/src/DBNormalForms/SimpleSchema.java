package DBNormalForms;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class SimpleSchema implements Schema {
    private final Set<String> attributes;
    private final Set<FunctionalDependency> dependencies = new HashSet<>();

    public SimpleSchema(Set<String> attributes, Set<FunctionalDependency> dependencies) {
        this.attributes = attributes;
        simplifyDependencies(dependencies);
    }

    private void simplifyDependencies(Set<FunctionalDependency> dependencies){
        Set<FunctionalDependency> set = new HashSet<>();
        for (FunctionalDependency fd : dependencies){
            set.addAll(fd.right()
                    .stream()
                    .filter(attributes::contains)
                    .map(A -> new CanonicalDependency(fd.left(), A)).toList());
        }
        Set<CanonicalDependency> minimalCover = Algorithm.minimalCover(set);
        this.dependencies.addAll(minimalCover);
        for (CanonicalDependency cd : minimalCover){
            if (!attributes.containsAll(cd.left()))
                this.dependencies.remove(cd);
        }
    }

    public Set<Set<String>> relations() {
        return Set.of(attributes);
    }

    public Set<FunctionalDependency> dependencies() {
        return dependencies;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SimpleSchema) obj;
        return Objects.equals(this.attributes, that.attributes) &&
                Objects.equals(this.dependencies, that.dependencies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributes, dependencies);
    }

    @Override
    public String toString() {
        return "SimpleSchema[" +
                "relations=" + attributes + ", " +
                "dependencies=" + dependencies + ']';
    }


}
