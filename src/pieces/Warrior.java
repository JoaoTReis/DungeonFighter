package pieces;

public class Warrior extends Heroes{
    private int especialMove; //int temporario

    public Warrior(int choice) {
        super(choice);
        setAttack(15);
        setDefense(20);
        setLife(10);
        setName("Warrior");
    }

    @Override
    public void ability() {
        //faz a habilidade
    }
}
