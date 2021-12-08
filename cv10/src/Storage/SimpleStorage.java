package Storage;

import java.util.LinkedList;

public class SimpleStorage implements Storage{
    private final LinkedList<Integer> queue = new LinkedList<>();

    @Override
    public void push(int x) {
        queue.push(x);
    }

    @Override
    public Integer pop() {
        if (queue.size() > 0)
            return queue.pop();
        else
            return null;
    }
}
