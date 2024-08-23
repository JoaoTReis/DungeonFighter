package pieces;

public class Warrior extends Heroes{

    public Warrior() {
        super(1, 1, 1, "Warrior");
    }


    @Override
    public String toString() {
        return "W";
    }


    public String printDescription() {
        return "Warrior Description: \n"
                +"defensor robusto que pode aumentar sua defesa em 50% por duas rodadas.\n";
    }
}
