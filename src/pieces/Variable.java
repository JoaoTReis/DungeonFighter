package pieces;

import java.util.concurrent.ThreadLocalRandom;

public class Variable extends Trap{
    public Variable() {
        super(ThreadLocalRandom.current().nextInt(0, 10));
    }

    @Override
    public String toString() {
        return "V";
    }
}
