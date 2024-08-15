package pieces;

public class Barbarian extends Heroes{
    private int especialMove; //int temporario

    public Barbarian(int choice) {
        super(choice);
        setAttack(20);
        setDefense(10);
        setLife(15);
        setName("Barbarian");
    }

    //@Override
    public void ability() {
        double ability = getAttack() * 1.5;
        setAttack((int) ability);
        System.out.println(ability);
    }

    @Override
    public String toString() {
        return getName()
                + "("
                + " Attack: "
                + getAttack()
                + " Defense: "
                + getDefense()
                + " Life: "
                + getLife()
                + ")";
    }

}
