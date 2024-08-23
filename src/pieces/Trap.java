package pieces;

public class Trap extends Items{
    private int dano;

    public Trap(int dano) {
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    public void damageHero(Heroes hero,Trap trap){
        hero.setLifeSubtract(trap.dano);
    }
}
