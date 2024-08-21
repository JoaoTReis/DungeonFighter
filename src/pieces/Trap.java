package pieces;

public class Trap extends Items{
    private int dano;

    public Trap(int dano) {
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }
}
