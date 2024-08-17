package pieces;

public class Characters {
    private int life;
    private int defense;
    private int attack;

    public Characters(int life, int defense, int attack) {
        this.life = life;
        this.defense = defense;
        this.attack = attack;
    }

    public int getLife() {
        return life;
    }

    protected void setLife(int life) {
        this.life += life;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense += defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack += attack;
    }
}
