package DBNormalForms;

import java.util.Set;

public interface Schema {
    Set<Set<String>> relations();
    Set<FunctionalDependency> dependencies();
}
