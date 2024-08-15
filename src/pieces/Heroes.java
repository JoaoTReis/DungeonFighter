package pieces;

    public class Heroes extends Characters{
        private final int choice;
        private Heroes selectedHero;

        public Heroes(int choice) {
            super();
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

        public Heroes getSelectedHero() {
            return selectedHero;
        }

    //public abstract void ability();
}
