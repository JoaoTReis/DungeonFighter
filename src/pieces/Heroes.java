package pieces;

    public class Heroes extends Characters{
        private String name;
        protected int abilitySpecial;
        protected int pontosXp;

        public Heroes(int life, int defense, int attack, String name) {
            super(life, defense, attack);
            this.name = name;
            setLife(10);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAbilitySpecial() {
            return abilitySpecial;
        }

        public void setAbilitySpecial(int abilitySpecial) {
            this.abilitySpecial = abilitySpecial;
        }

        public int getPontosXp() {
            return pontosXp;
        }

        public int setPontosXp(int pontosXp) {
            this.pontosXp = pontosXp;
            return pontosXp;
        }
    }
