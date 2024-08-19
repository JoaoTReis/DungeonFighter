package pieces;

public class NormalMonster extends Monster{
    public NormalMonster() {
        super(5, 5, 5,"Henchman");
    }

    @Override
    public String toString() {
        return "N";
    }
}
