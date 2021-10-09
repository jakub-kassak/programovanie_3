package dnd;

import java.util.Random;

public class StandardRandom implements MyRandom {
    private final Random random = new Random();

    @Override
    public long nextLong() {
        return random.nextLong();
    }
}
