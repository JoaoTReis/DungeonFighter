package pieces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class Heroes extends Characters {
    protected int abilitySpecial;
    protected int pontosXp = 45;
    private Elixir bolsa;
    private static boolean isInterfaceShown = false; // Variável de controle para garantir que a tela seja exibida apenas uma vez
    private JFrame frame;

    public Heroes() {
        super(0, 0, 0, null);
    }

    public Heroes(int life, int defense, int attack, String name) {
        super(life, defense, attack, name);
        setLifeSum(10);
        bolsa = new Elixir(this);
    }

    public Heroes decideHeroi(int choice) {
        char decisao = 0;
        Heroes choiceHero = null;

        do {
            if (decisao == 'n') {
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    Warrior warrior = new Warrior();
                    System.out.println(warrior.printDescription());
                    System.out.println("Deseja realmente utilizar esta classe S/n?");
                    decisao = sc.next().charAt(0);
                    if (decisao == 's') {
                        choiceHero = warrior;
                    }
                    break;
                case 2:
                    Paladin paladin = new Paladin();
                    System.out.println(paladin);
                    System.out.println("Deseja realmente utilizar esta classe S/n?");
                    decisao = sc.next().charAt(0);
                    if (decisao == 's') {
                        choiceHero = paladin;
                    }
                    break;
                case 3:
                    Barbarian barbarian = new Barbarian();
                    System.out.println(barbarian);
                    System.out.println("Deseja realmente utilizar esta classe S/n?");
                    decisao = sc.next().charAt(0);
                    if (decisao == 's') {
                        choiceHero = barbarian;
                    }
                    break;
                default:
            }
        } while (decisao == 'n');
        if (choiceHero!=null){
            choiceHero.distribuiPontos(pontosXp);
        }
        return choiceHero;

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



    public void distribuiPontosGanho(int pontosGanhos) {

        do {
            System.out.println("Onde deseja adicionar pontos: ");
            System.out.println("voce tem (" + pontosGanhos + ") pontos");
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

    public void ganhaPontos() {
        pontosXp = ThreadLocalRandom.current().nextInt(1, 11);
        distribuiPontosGanho(pontosXp);
    }

    public void usaElixir() {
        if(bolsa.getQtdElixir()>0) {
            System.out.println("Quantos elixires deseja usar? Voce tem"+bolsa.getQtdElixir()+"na bolsa");
            int escolha = sc.nextInt();
                if (escolha > bolsa.getQtdElixir()) {
                    System.out.println("Quantidade de elixires para uso insuficiente voce tem: "+bolsa.getQtdElixir());
                } else {
                    while (bolsa.getQtdElixir() > 0) {
                    bolsa.setQtdElixir(bolsa.getQtdElixir() - escolha);
                    int vidaRecuperada = escolha * 3;
                    setLifeSum(vidaRecuperada);
                    System.out.println("Você usou " + escolha
                            + " elixires e recuperou " + vidaRecuperada
                            + " pontos de vida.\n"
                            +" Vida atual:"+  getLife());
                }
            }
        }else{
            System.out.println("A bolsa esta vazia!");
        }
    }

    public void heroAlive() {
        if (!isAlive()) {
            System.out.println("You lost, hero died");
            System.exit(0);
        }
    }

}