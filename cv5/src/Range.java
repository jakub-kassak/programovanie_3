import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer>, Iterator<Integer> {
    private int upperBound;
    private int current;
    private int step = 1;

    public Range(int upperBound){
        this.upperBound = upperBound;
    }

    public Range(int lowerBound, int upperBound){
        this.upperBound = upperBound;
        this.current = lowerBound;
    }

    public Range(int lowerBound, int upperBound, int step){
        this.upperBound = upperBound;
        this.current = lowerBound;
        this.step = step;
    }

    public boolean hasNext() {
        if (step > 0)
            return current < upperBound;
        else
            return current > upperBound;
    }

    public Integer next() {
        if (!hasNext())
            throw new NoSuchElementException();
        int ret = current;
        current += step;
        return ret;
    }

    public Iterator<Integer> iterator() {
        return this;
    }
}
