package pieces;

import java.util.Scanner;

public class Characters{

    protected Scanner sc = new Scanner(System.in);
    private String name;
    private int life;
    private int defense;
    private int attack;

    public Characters(int life, int defense, int attack,String name) {
        this.life = life;
        this.defense = defense;
        this.attack = attack;
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public boolean isAlive(){
        return getLife() > 0;
    }

    public void setLifeSum(int life) {
        this.life += life;
    }

    public void setLifeSubtract(int life) {
        this.life -= life;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String printAttributes(){
        return getName()+"{ "+"Life: "+getLife()+" Defense: "+getDefense()+"Attack: "+getAttack()+"}";
    }
}
