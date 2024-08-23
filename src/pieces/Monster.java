package pieces;

public class Monster extends Characters{

    public Monster() {
        super(0,0,0,null);
    }

    public Monster(int life, int defense, int attack, String name) {
        super(life, defense, attack,name);
    }
}
