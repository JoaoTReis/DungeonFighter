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

    @Override
    public void ability() {
        //faz a habilidade
    }

}
