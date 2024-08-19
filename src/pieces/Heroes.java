package pieces;

public class Heroes extends Characters{
        private String name;
        protected int abilitySpecial;
        protected int pontosXp;
        private Elixir bolsa = new Elixir();

        public Heroes() {
            super(0,0,0);
        }

        public Heroes(int life, int defense, int attack, String name) {
            super(life, defense, attack);
            this.name = name;
            setLife(10);
        }

        public Characters decideHeroi(){
            char decisao = 0;
            Characters selectedHero = null;

            do {
                System.out.print("choose your hero:");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        Warrior warrior = new Warrior();
                        System.out.println(warrior.toStringDescription());
                        System.out.println("Deseja realmente utilizar esta classe S/n?");
                        decisao = sc.next().charAt(0);
                        if (decisao == 's') {
                            warrior.distribuiPontos();
                            selectedHero = warrior;
                        }
                        break;
                    case 2:
                        Paladin paladin = new Paladin();
                        System.out.println(paladin);
                        System.out.println("Deseja realmente utilizar esta classe S/n?");
                        decisao = sc.next().charAt(0);
                        if (decisao == 's') {
                            paladin.distribuiPontos();
                        }
                        break;
                    case 3:
                        Barbarian barbarian = new Barbarian();
                        System.out.println(barbarian);
                        System.out.println("Deseja realmente utilizar esta classe S/n?");
                        decisao = sc.next().charAt(0);
                        if (decisao == 's') {
                            barbarian.distribuiPontos();
                        }
                        break;
                    default:
                }
            }while(decisao == 'n');
            return selectedHero;
        }

        public String getName() {
            return name;
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

        protected void usaElixir(Elixir elixir){
            System.out.println("Quantos elixires deseja usar? ");
            int escolha = sc.nextInt();
            while (bolsa.qtdElixir > 0){
                if(escolha > bolsa.qtdElixir) {
                    throw new IllegalArgumentException("Erro, esta tentando usar mais elixires do que possui");
                }else {
                    bolsa.qtdElixir -= escolha;
                    escolha *= 3;
                    setLife(escolha);

                }
            }
        }
    }
