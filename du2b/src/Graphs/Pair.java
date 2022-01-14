package Graphs;

import java.util.Objects;

public class Pair<I, II> {
    public final I first;
    public final II second;

    public Pair(I first, II second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "[" + first.toString() + ", " + second.toString()+ "]";
    }

    @Override
    public boolean equals(Object obj) {
        // self check
        if (this == obj)
            return true;
        // null check
        if (obj == null)
            return false;
        // type check and cast
        if (getClass() != obj.getClass())
            return false;
        else {
            @SuppressWarnings (value="unchecked")
            Pair<I, II> otherPair = (Pair<I, II>) obj;
            // field comparison
            return Objects.equals(first, otherPair.first)
                    && Objects.equals(second, otherPair.second);
        }
    }
}

