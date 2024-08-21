package pieces;

import java.util.concurrent.ThreadLocalRandom;

public class Boss extends Monster{
    public int atributosBoss(int atributo) {
        int ramdomStatus = ThreadLocalRandom.current().nextInt(1, 10);
        return atributo *ramdomStatus;
    }

    public Boss() {
        setLifeSum(atributosBoss(5));
        setAttack(atributosBoss(5));
        setDefense(atributosBoss(5));
        setName("The Boss");
    }

    @Override
    public String toString() {
        return "B";
    }
}
