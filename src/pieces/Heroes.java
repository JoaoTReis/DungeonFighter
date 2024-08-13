package pieces;

public abstract class Heroes extends Characters{
    private int choice;

    public Heroes(int choice) {
        this.choice = choice;
    }

    public Heroes selectHero(){
        switch (choice){
            case 1:
                return new Warrior(choice);
            case 2:
                return new Paladin(choice);
            case 3:
                return new Barbarian(choice);
            default:
                throw new IllegalArgumentException("this choice is invalidate");
        }
    }

    public abstract void ability();
}
