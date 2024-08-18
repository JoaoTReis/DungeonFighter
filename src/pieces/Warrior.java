package pieces;

import java.util.Scanner;

public class Warrior extends Heroes{

    public Warrior() {
        super(1, 1, 1, "Warrior");
    }

    public void distribuiPontos(){
        int pontos = setPontosXp(45);
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
                        setLife(pontosVida);
                        pontos -= pontosVida;
                        setPontosXp(pontos);
                        break;
                    case 2:
                        System.out.println("Quantos pontos deseja acrecentar nessa habilidade? pontos (" + getPontosXp() + ")");
                        int pontosDefesa = sc.nextInt();
                        setDefense(pontosDefesa);
                        pontos -= pontosDefesa;
                        setPontosXp(pontos);
                        break;
                    case 3:
                        System.out.println("Quantos pontos deseja acrecentar nessa habilidade? pontos (" + getPontosXp() + ")");
                        int pontosAtaque = sc.nextInt();
                        setAttack(pontosAtaque);
                        pontos -= pontosAtaque;
                        setPontosXp(pontos);
                        break;
                    default:
                        throw new IllegalArgumentException("Entrada invalida!");
                }
            }while(getPontosXp()>0);

    }


    @Override
    public String toString() {
        return "W";
    }

    public String toStringDescription() {
        return "Warrior Description: \n"
                +"defensor robusto que pode aumentar sua defesa em 50% por duas rodadas.\n";
    }
}
