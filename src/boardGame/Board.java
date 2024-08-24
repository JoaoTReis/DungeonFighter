package boardGame;

import gameActions.PerformActions;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

//tem a decaracao de linhas e colunas para determinar tamanho, position é a posicao no tabuleiro
public class Board {
    Scanner sc = new Scanner(System.in);
    //numero de pecas
    private final int NUMBERPICESSIZE = 5;

    //tamanho da matriz
    private final int ROWSIZE = 5;
    private final int COLUMNSIZE = 10;

    //linhas da posicao do heroi
    private int heroiRow;
    private int heroiColumn;

    //Pecas no tabueiro
    private Characters hero;
    private Characters boss;
    private Characters[] normalMonster;
    private Elixir[] elixirs;
    private Trap[] trapVariable;
    private Trap[] trapFixed;

    //tabuleiro
    private Piece[][] pieces;
    private PerformActions actions;

    //turno e jogador
    private int turn;
    private Characters currentPlayer;

    public Board(){
        System.out.println("Choice your hero: ");
        int choice = sc.nextInt();
        hero=new Heroes().decideHeroi(choice);

        heroiRow = 0;
        heroiColumn = 0;
        boss= new Boss();
        elixirs = new Elixir[NUMBERPICESSIZE];
        normalMonster = new NormalMonster[NUMBERPICESSIZE];
        trapVariable = new Variable[NUMBERPICESSIZE];
        trapFixed = new Fixed[NUMBERPICESSIZE];

        pieces = new Piece[ROWSIZE][COLUMNSIZE];
        actions = new PerformActions(this);

        //define os atributos do turno e do personagem no turno
        turn = 1;
        currentPlayer = getHero();

        initBoard();

        printfBoard();
        while (hero.isAlive()) {
            System.out.println("O que deseja fazer");
            System.out.println("1 Movimentar heroi, 2 usar elixir, 3 verificar armadinha (apenas "+remainingTips+" usos)");
            int choiceAction = sc.nextInt();
            switch (choiceAction){
                case 1->moveHero();
                case 2->((Heroes)getHero()).usaElixir();
                case 3->checkTrapsInDirections();
            }
            printfBoard();
        }
    }

    //Geters


    public Piece getPieces(int row,int column) {
        return pieces[row][column];
    }

    public int getHeroiRow() {
        return heroiRow;
    }

    public int getHeroiColumn() {
        return heroiColumn;
    }

    public Characters getHero() {
        return hero;
    }

    public Characters[] getNormalMonster() {
        return normalMonster;
    }

    public Characters getBoss() {
        return boss;
    }

    public Characters getCurrentPlayer() {
        return currentPlayer;
    }

    public int getTurn() {
        return turn;
    }

    //metodo que inicializa o tabuleiro
    public void initBoard(){

        for(int i=0;i< pieces.length;i++){
            for(int j=0;j<pieces[i].length;j++){
                pieces[i][j]= new Piece();
            }
        }

        pieces[heroiRow][heroiColumn].addCharacter(hero);

        //5 numero de elixir
        distribui(PieceType.ELIXIR);

        //5 numero de monstroNormal
        distribui(PieceType.MONSTER);

        //5 numero de trapVariable
        distribui(PieceType.VARIABLE_TRAP);

        //5 numero de trapFixed
        distribui(PieceType.FIXED_TRAP);

        //boss
        distribui(PieceType.BOSS);
    }

    private boolean hasPiece(int row,int column) {
        return (row != 0 || column != 0) && pieces[row][column].hasPiece();
    }

    private void distribui(PieceType pieceType) {
        int i=0;

        while(i< NUMBERPICESSIZE) {
            int ramdomRow = dado(0,ROWSIZE);
            int ramdomColumn = dado(0,COLUMNSIZE);
            if (pieceType != PieceType.BOSS) {
                if (hasPiece(ramdomRow, ramdomColumn)) {
                    if (pieceType == PieceType.ELIXIR) {
                        elixirs[i] = new Elixir((Heroes)hero);
                        pieces[ramdomRow][ramdomColumn].addItems(elixirs[i]);
                    } else if (pieceType == PieceType.MONSTER) {
                        normalMonster[i] = new NormalMonster();
                        pieces[ramdomRow][ramdomColumn].addCharacter(normalMonster[i]);
                    } else if (pieceType == PieceType.VARIABLE_TRAP) {
                        trapVariable[i] = new Variable();
                        pieces[ramdomRow][ramdomColumn].addItems(trapVariable[i]);
                    } else if (pieceType == PieceType.FIXED_TRAP) {
                        trapFixed[i] = new Fixed();
                        pieces[ramdomRow][ramdomColumn].addItems(trapFixed[i]);
                    }
                    i++;
                }
            }else {
                if (hasPiece(ramdomRow, ramdomColumn)) {
                    boss = new Boss();
                    if (ramdomRow > ramdomColumn) {
                        pieces[4][ramdomColumn].addCharacter(boss);
                    } else {
                        pieces[ramdomRow][9].addCharacter(boss);
                    }
                    i=NUMBERPICESSIZE;
                }
            }
        }
    }

    public void moveHero() {
        int newRow = heroiRow;
        int newColumn = heroiColumn;

        System.out.println("Digite a nova posição do herói:");
        newRow = sc.nextInt();
        newColumn = sc.nextInt();

        if (isValidPosition(newRow, newColumn) && isAdjacentPosition(newRow, newColumn)) {
            updateHeroPosition(newRow, newColumn);
        } else {
            System.out.println("Movimento inválido! O herói só pode se mover para casas adjacentes e dentro dos limites do tabuleiro.");
        }
    }

    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < ROWSIZE && column >= 0 && column < COLUMNSIZE;
    }

    private boolean isAdjacentPosition(int newRow, int newColumn) {
        int rowDifference = Math.abs(newRow - heroiRow);
        int columnDifference = Math.abs(newColumn - heroiColumn);
        return (rowDifference == 1 && columnDifference == 0) || (rowDifference == 0 && columnDifference == 1);
    }

    private void updateHeroPosition(int newRow, int newColumn) {
        if (newRow != heroiRow || newColumn != heroiColumn) {
            System.out.println("Movendo o herói");

            pieces[heroiRow][heroiColumn].removePieceCharecters(hero);
            heroiRow = newRow;
            heroiColumn = newColumn;
            pieces[heroiRow][heroiColumn].addCharacter(hero);

            actions.fight(pieces[heroiRow][heroiColumn]);
            actions.getElixir(pieces[heroiRow][heroiColumn]);
            actions.damageTrap(pieces[heroiRow][heroiColumn]);
            ((Heroes) getHero()).heroAlive();
        }
    }

    private int remainingTips = 3; // Variável de controle para o número de usos restantes da dica

    private void checkTrapsInDirections() {
        if (remainingTips <= 0) {
            System.out.println("Você já usou todas as dicas disponíveis.");
            return;
        }

        boolean trapFound = false;
        StringBuilder message = new StringBuilder("Traps encontradas:\n");

        int[][] directions = {
                {1, 0}, // Abaixo
                {-1, 0}, // Acima
                {0, 1}, // Direita
                {0, -1} // Esquerda
        };

        String[] directionNames = {"abaixo", "acima", "direita", "esquerda"};

        for (int i = 0; i < directions.length; i++) {
            int adjRow = heroiRow + directions[i][0];
            int adjColumn = heroiColumn + directions[i][1];

            if (isValidPosition(adjRow, adjColumn) &&
                    (pieces[adjRow][adjColumn].hasTrapVariable() || pieces[adjRow][adjColumn].hastrapFixed())) {
                message.append("Há uma trap ").append(directionNames[i]).append(" do herói.\n");
                trapFound = true;
            }
        }

        if (!trapFound) {
            message.append("Não há traps nas direções adjacentes.");
        }

        System.out.println(message.toString());

        remainingTips--; // Decrementa o número de usos restantes
    }



    public int nextTurn() {
        currentPlayer = (currentPlayer == getHero() && getPieces(heroiRow, heroiColumn).getMonster() != null)
                ? getPieces(heroiRow, heroiColumn).getMonster()
                : getHero();
        return turn++;
    }

    public int dado(int maisBaixo,int maisAlto){
        return ThreadLocalRandom.current().nextInt(maisBaixo,maisAlto);
    }

    public String getActualTurnInfo(){
        return ""+getTurn();
    }

    public String getActualCurrentPlayer(){
        return "" + getCurrentPlayer();
    }



    public void printfBoard(){
        for(int i=0;i< pieces.length;i++){
            for(int j=0;j<pieces[i].length;j++){
                pieces[i][j].printPieces();
            }
            System.out.println("|");
        }
        hero.printAttributes();
    }
}
