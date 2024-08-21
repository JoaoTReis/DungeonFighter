package gameActions;

import boardGame.Board;
import boardGame.Piece;
import pieces.*;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PerformActions implements Actions{

    Scanner sc = new Scanner(System.in);
    private Board board;


    public PerformActions(Board board) {
        this.board = board;
    }

    @Override
    public void fight(Piece hero) {
            Piece currentPiece = board.getPieces(board.getHeroiRow(),board.getHeroiColumn());
            if(currentPiece.hasNormalMonster()){
                for(Characters monster : currentPiece.getCharacters()){
                    if(monster instanceof NormalMonster){
                        System.out.println("LUTA!!");
                        System.out.println(board.getHero().printAttributes());
                        System.out.println(monster.printAttributes());
                        System.out.println("Opcoes de luta:");
//                        System.out.print("|1 atacar| |2 habilidade especial| |3 usar elixir| |4 Correr|");
//                        int choice = sc.nextInt();
//                        switch (choice){
//                            case 1:
                                while(board.getHero().getLife()>0 && monster.getLife()>0) {
                                    int dadoAtaque = ThreadLocalRandom.current().nextInt(1, 7);
                                    int dadoDefesa  = ThreadLocalRandom.current().nextInt(1, 7);
                                    System.out.println("Ataque Heroi" + (board.getHero().getAttack() + dadoAtaque) + "Defesa monstro" + (monster.getDefense()+dadoDefesa));
                                    if (board.getHero().getAttack() + dadoAtaque > monster.getDefense()+dadoDefesa) {
                                        monster.setLifeSubtract(board.getHero().getAttack()+dadoAtaque-monster.getDefense()+dadoDefesa);
                                        System.out.println(board.getHero().printAttributes());
                                        System.out.println(monster.printAttributes());
                                    }
                                }
//                            case 2:
//                            case 3:
//                            case 4:
//                        }
                    }
                }
            }

//            while (board.getHero().getLife() != 0){
//                System.out.println(board.getHero().getLife());
//                board.getHero().setLife(0);
//            }
        if(hero.hasBoss()){
            System.out.println("Boss Fight!!");
            System.out.println(board.getHero().printAttributes());
            System.out.println(board.getBoss().printAttributes());

        }
    }

    @Override
    public void getElixir(Piece hero) {
          if(hero.hasElixir()){
              System.out.println("Elixir!");
          }
    }

    @Override
    public void damageTrap(Piece hero) {
        if(hero.hastrapFixed()){
            System.out.println("Trap Fixed!!");
        }
        if(hero.hasTrapVariable()){
            System.out.println("Trap Variable!!");
        }
    }
}
