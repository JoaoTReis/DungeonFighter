package pieces;

public class Monster extends Characters{

    private String name;

    public Monster(){
        super(0,0,0);

    }

    public Monster(int life, int defense, int attack,String name) {
        super(life, defense, attack);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
