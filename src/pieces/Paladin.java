package pieces;

public class Paladin extends Heroes{
    private int especialMove; //int temporario

    public Paladin(int choice) {
        super(choice);
        setAttack(10);
        setDefense(15);
        setLife(20);
        setName("Paladin");
    }

    @Override
    public void ability() {
        //faz a habilidade
    }

}
