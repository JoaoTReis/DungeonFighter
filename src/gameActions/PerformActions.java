package gameActions;

import boardGame.Board;
import boardGame.Piece;
import pieces.*;

import java.util.Scanner;


public class PerformActions implements Actions{

    Scanner sc = new Scanner(System.in);
    private Board board;

    public PerformActions(Board board) {
        this.board = board;
    }


    private void processAttack(Characters attacker, Characters defender) {
        int dadoAtaque = board.dado(1, 7);
        int dadoDefesa = board.dado(1, 7);
        System.out.println("Ataque " + attacker.getName() + ": " + (attacker.getAttack() + dadoAtaque) + " Defesa " + defender.getName() + ": " + (defender.getDefense() + dadoDefesa));

        if (attacker.getAttack() + dadoAtaque > defender.getDefense() + dadoDefesa) {
            defender.setLifeSubtract((attacker.getAttack() + dadoAtaque) - (defender.getDefense() + dadoDefesa));
            System.out.println(attacker.printAttributes());
            System.out.println(defender.printAttributes());
        }
    }

    private void handleNormalMonsterFight(Characters monster) {
        System.out.println("LUTA!!");
        System.out.println(board.getHero().printAttributes());
        System.out.println(monster.printAttributes());
        System.out.println("Opções de luta:");

        while (board.getHero().getLife() > 0 && monster.getLife() > 0) {
            board.nextTurn();

            if (board.getCurrentPlayer().equals(board.getHero())) {
                processAttack(board.getHero(), monster);
            } else if (board.getCurrentPlayer().equals(monster)) {
                processAttack(monster, board.getHero());
            }

            ((Heroes) board.getHero()).heroAlive();
            if (!monster.isAlive()) {
                ((Heroes) board.getHero()).ganhaPontos();
                board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).removePieceCharecters(monster);
                break;
            }
        }
    }

    private void handleBossFight(Characters boss) {
        System.out.println("Boss Fight!!");
        System.out.println(board.getHero().printAttributes());
        System.out.println(boss.printAttributes());
        System.out.println("Opções de luta:");
        System.out.print("|1 atacar| |2 habilidade especial| |3 usar elixir| |4 Correr|");

        while (board.getHero().getLife() > 0 && boss.getLife() > 0) {
            board.nextTurn();

            if (board.getCurrentPlayer().equals(board.getHero())) {
                processAttack(board.getHero(), boss);
            } else if (board.getCurrentPlayer().equals(boss)) {
                processAttack(boss, board.getHero());
            }

            ((Heroes) board.getHero()).heroAlive();
            if (!boss.isAlive()) {
                ((Heroes) board.getHero()).ganhaPontos();
                board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).removePieceCharecters(boss);
                break;
            }
        }

        // Lógica de fuga
        int dadoCorre1 = board.dado(1, 3);
        int dadoCorre2 = board.dado(1, 3);
        if (dadoCorre1 == dadoCorre2) {
            // Supondo que board.moveHero(KeyEvent) faz algo quando o herói foge
        }
    }

    @Override
    public void fight(Piece hero) {
        Piece currentPiece = board.getPieces(board.getHeroiRow(), board.getHeroiColumn());

        if (currentPiece.hasNormalMonster()) {
            for (Characters monster : currentPiece.getCharacters()) {
                if (monster instanceof NormalMonster) {
                    handleNormalMonsterFight(monster);
                    break; // Após lutar com o monstro, termina o loop
                }
            }
        }

        if (hero.hasBoss()) {
            for (Characters boss : currentPiece.getCharacters()) {
                if (boss instanceof Boss) {
                    handleBossFight(boss);
                    break; // Após lutar com o chefe, termina o loop
                }
            }
        }
    }

    @Override
    public void getElixir(Piece hero) {
        if(hero.hasElixir())
            for (int i = 0; i < board.getPieces(board.getHeroiRow(),board.getHeroiColumn()).getItems().size(); i++) {
                Items item = board.getPieces(board.getHeroiRow(),board.getHeroiColumn()).getItems().get(i);
                    System.out.println("Elixir!");
//                    System.out.println("Deseja pegar o elixir?");
//                    System.out.println("1 sim 2 nao");
//                    int choice = sc.nextInt();
//                    switch (choice){
//                        case 1:
                            if(item instanceof Elixir) {
                                if(Elixir.scholarshipLimit()) {
                                    Elixir.elixirToBag();
                                    board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).removePieceItems(board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).getItems().get(i));
                                }else{
                                    Elixir.elixirToBag();
                                }
                            }
//                        case 2:
//
//                    }

            }
    }

    @Override
    public void damageTrap(Piece hero) {
        if(hero.hastrapFixed()){
            for (int i = 0; i < board.getPieces(board.getHeroiRow(),board.getHeroiColumn()).getItems().size(); i++) {
                Items trap = board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).getItems().get(i);
                System.out.println("Trap Fixed!!");
                if(trap instanceof Fixed){
                    ((Fixed) trap).damageHero((Heroes) board.getHero(),(Trap) trap);
                    System.out.println(board.getHero().getLife());
                }
            }
        }
        if(hero.hasTrapVariable()){
            for (int i = 0; i < board.getPieces(board.getHeroiRow(),board.getHeroiColumn()).getItems().size(); i++) {
                Items trap = board.getPieces(board.getHeroiRow(), board.getHeroiColumn()).getItems().get(i);
                System.out.println("Trap Variable!!");
                if(trap instanceof Variable){
                    ((Variable) trap).damageHero((Heroes) board.getHero(),(Trap) trap);
                    System.out.println("Dano da trap variavel:"+((Variable) trap).getDano());
                }
            }
        }
    }
}
