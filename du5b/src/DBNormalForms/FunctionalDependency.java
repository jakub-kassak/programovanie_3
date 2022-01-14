package DBNormalForms;

import java.util.Set;

public interface FunctionalDependency {

    Set<String> left();
    Set<String> right();
}
