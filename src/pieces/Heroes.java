package pieces;

import java.util.concurrent.ThreadLocalRandom;

public class Heroes extends Characters{
        protected int abilitySpecial;
        protected int pontosXp = 45;
        private Elixir bolsa = new Elixir();

        public Heroes() {
            super(0,0,0,null);
        }

        public Heroes(int life, int defense, int attack, String name) {
            super(life, defense, attack, name);
            setLifeSum(10);
        }

        public Heroes decideHeroi(int choice){
            char decisao = 0;

            do {
                switch (choice) {
                    case 1:
                        Warrior warrior = new Warrior();
                        System.out.println(warrior.printDescription());
                        System.out.println("Deseja realmente utilizar esta classe S/n?");
                        decisao = sc.next().charAt(0);
                        if (decisao == 's') {
                            warrior.distribuiPontos(pontosXp);
                            return warrior;
                        }
                        break;
                    case 2:
                        Paladin paladin = new Paladin();
                        System.out.println(paladin);
                        System.out.println("Deseja realmente utilizar esta classe S/n?");
                        decisao = sc.next().charAt(0);
                        if (decisao == 's') {
                            paladin.distribuiPontos();
                            return paladin;
                        }
                        break;
                    case 3:
                        Barbarian barbarian = new Barbarian();
                        System.out.println(barbarian);
                        System.out.println("Deseja realmente utilizar esta classe S/n?");
                        decisao = sc.next().charAt(0);
                        if (decisao == 's') {
                            barbarian.distribuiPontos();
                            return barbarian;
                        }
                        break;
                    default:
                }
            }while(decisao == 'n');
            return null;
        }

    public void distribuiPontos(int pontos){
        int pontosXp = pontos;
        do {
            System.out.println("Onde deseja adicionar pontos: ");
            System.out.println("voce tem (" + getPontosXp() + ") pontos");
            System.out.println("Vida (" + getLife() + ")");
            System.out.println("Defesa (" + getDefense() + ")");
            System.out.println("Ataque (" + getAttack() + ")");

            System.out.println("Qual atributo deseja adicionar pontos?");
            System.out.println("1 Vida");
            System.out.println("2 Defesa");
            System.out.println("3 Ataque");
            int escolha = sc.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Quantos pontos deseja acrecentar nessa habilidade? pontos (" + getPontosXp() + ")");
                    int pontosVida = sc.nextInt();
                    setLifeSum(pontosVida);
                    pontosXp -= pontosVida;
                    setPontosXp(pontosXp);
                    break;
                case 2:
                    System.out.println("Quantos pontos deseja acrecentar nessa habilidade? pontos (" + getPontosXp() + ")");
                    int pontosDefesa = sc.nextInt();
                    setDefense(pontosDefesa);
                    pontosXp -= pontosDefesa;
                    setPontosXp(pontosXp);
                    break;
                case 3:
                    System.out.println("Quantos pontos deseja acrecentar nessa habilidade? pontos (" + getPontosXp() + ")");
                    int pontosAtaque = sc.nextInt();
                    setAttack(pontosAtaque);
                    pontosXp -= pontosAtaque;
                    setPontosXp(pontosXp);
                    break;
                default:
                    throw new IllegalArgumentException("Entrada invalida!");
            }
        }while(getPontosXp()>0);

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

        public boolean arePoints(){
            return getPontosXp()>0;
        }



        public void distribuiPontosGanho() {
            ganhaPontos();
            do {
                System.out.println("Onde deseja adicionar pontos: ");
                System.out.println("voce tem (" + getPontosXp() + ") pontos");
                System.out.println("Vida (" + getLife() + ")");
                System.out.println("Defesa (" + getDefense() + ")");
                System.out.println("Ataque (" + getAttack() + ")");

                System.out.println("Qual atributo deseja adicionar pontos?");
                System.out.println("1 Vida");
                System.out.println("2 Defesa");
                System.out.println("3 Ataque");
                int escolha = sc.nextInt();
                switch (escolha) {
                    case 1:
                        System.out.println("Quantos pontos deseja acrecentar nessa habilidade? pontos (" + getPontosXp() + ")");
                        int pontosVida = sc.nextInt();
                        setLifeSum(pontosVida);
                        pontosXp -= pontosVida;
                        setPontosXp(pontosXp);
                        break;
                    case 2:
                        System.out.println("Quantos pontos deseja acrecentar nessa habilidade? pontos (" + getPontosXp() + ")");
                        int pontosDefesa = sc.nextInt();
                        setDefense(pontosDefesa);
                        pontosXp -= pontosDefesa;
                        setPontosXp(pontosXp);
                        break;
                    case 3:
                        System.out.println("Quantos pontos deseja acrecentar nessa habilidade? pontos (" + getPontosXp() + ")");
                        int pontosAtaque = sc.nextInt();
                        setAttack(pontosAtaque);
                        pontosXp -= pontosAtaque;
                        setPontosXp(pontosXp);
                        break;
                    default:
                        throw new IllegalArgumentException("Entrada invalida!");
                }
            }while(getPontosXp()>0);
        }

    public int ganhaPontos() {
            return ThreadLocalRandom.current().nextInt(1, 11);
    }


    protected void usaElixir(Elixir elixir){
            System.out.println("Quantos elixires deseja usar? ");
            int escolha = sc.nextInt();
            while (Elixir.qtdElixir > 0){
                if(escolha > Elixir.qtdElixir) {
                    throw new IllegalArgumentException("Erro, esta tentando usar mais elixires do que possui");
                }else {
                    bolsa.qtdElixir -= escolha;
                    escolha *= 3;
                    setLifeSum(escolha);

                }
            }
        }

        public void heroAlive(){
            if(!isAlive()){
                System.out.println("You lost, hero died");
                System.exit(0);
            }
        }
    }
