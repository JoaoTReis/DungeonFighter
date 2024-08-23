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
public class Board extends JPanel {
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

    private JLabel messageLabel;

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

        messageLabel =new JLabel("Use the arrow keys to move."); // Adiciona um JLabel para mensagens
        this.add(messageLabel, BorderLayout.SOUTH);
        setLayout(new BorderLayout());


        // Adiciona o KeyListener para capturar as teclas
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moveHero(e);
                //pegaElixir();
                repaint();
            }
        });
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
        int ramdomRow;
        int ramdomColumn;
        while(i< NUMBERPICESSIZE) {
            if (pieceType != PieceType.BOSS) {
                ramdomRow = ThreadLocalRandom.current().nextInt(0, ROWSIZE);
                ramdomColumn = ThreadLocalRandom.current().nextInt(0, COLUMNSIZE);
                if (hasPiece(ramdomRow, ramdomColumn)) {
                    if (pieceType == PieceType.ELIXIR) {
                        elixirs[i] = new Elixir();
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
                ramdomRow = ThreadLocalRandom.current().nextInt(0, ROWSIZE);
                ramdomColumn = ThreadLocalRandom.current().nextInt(0, COLUMNSIZE);
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

    public void moveHero(KeyEvent e){
        int newRow = heroiRow;
        int newColumn = heroiColumn;

        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                newRow = Math.max(0, heroiRow - 1);
                break;
            case KeyEvent.VK_DOWN:
                newRow = Math.min(ROWSIZE - 1, heroiRow + 1);
                break;
            case KeyEvent.VK_LEFT:
                newColumn = Math.max(0, heroiColumn - 1);
                break;
            case KeyEvent.VK_RIGHT:
                newColumn = Math.min(COLUMNSIZE - 1, heroiColumn + 1);
                break;
        }

        if (newRow != heroiRow || newColumn != heroiColumn) {
            System.out.println("O que deseja fazer");
            System.out.println("1 Usar pocao, 2 mover heroi");

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

    public void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == getHero() && getPieces(heroiRow, heroiColumn).getMonster() != null)
                ? getPieces(heroiRow, heroiColumn).getMonster()
                : getHero();

        // Após atualizar o turno, atualize o JLabel
        //updateInfoLabel();
    }
//    public void removePiece(Piece piece){
//        piece.removePiece(pieces[heroiRow][heroiColumn]);
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < ROWSIZE; i++) {
            for (int j = 0; j < COLUMNSIZE; j++) {
                g.drawRect(j * 50, i * 50, 50, 50);
                g.drawString(pieces[i][j].toString(), j * 50 + 20, i * 50 + 30);
            }
        }
    }

    public String getTurnInfo(){
        return "Turn: "+ getTurn();
    }

    public void printfBoard(){
        for(int i=0;i< pieces.length;i++){
            for(int j=0;j<pieces[i].length;j++){
                pieces[i][j].printPieces();
            }
            System.out.println("|");
        }
    }

}
