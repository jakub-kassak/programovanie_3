package DBNormalForms;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ComplexSchema implements Schema{
    private final Set<Schema> schemas = new HashSet<>();

    public ComplexSchema(Set<Schema> schemas) {
        this.schemas.addAll(schemas);
    }

    @Override
    public Set<Set<String>> relations() {
        return schemas.stream()
                .map(Schema::relations)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FunctionalDependency> dependencies() {
        return schemas.stream()
                .map(Schema::dependencies)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "ComplexSchema{" +
                "schemas=" + schemas +
                '}';
    }
}
