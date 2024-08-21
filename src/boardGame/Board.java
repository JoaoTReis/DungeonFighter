package boardGame;

import gameActions.PerformActions;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

//tem a decaracao de linhas e colunas para determinar tamanho, position é a posicao no tabuleiro
public class Board extends JPanel {
    //tamanho da matriz
    private int row = 5;
    private int column = 10;

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
    PerformActions actions;

    private JLabel messageLabel;

    public Board(){
        hero=new Heroes().decideHeroi();
        heroiRow = 0;
        heroiColumn = 0;
        boss= new Boss();
        elixirs = new Elixir[5];
        normalMonster = new NormalMonster[5];
        trapVariable = new Variable[5];
        trapFixed = new Fixed[5];

        pieces = new Piece[row][column];
        actions = new PerformActions(this);

        initBoard();

        messageLabel =new JLabel("Use the arrow keys to move."); // Adiciona um JLabel para mensagens
        this.add(messageLabel, BorderLayout.SOUTH);

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

    //metodo que inicializa o tabuleiro
    public void initBoard(){

        for(int i=0;i< pieces.length;i++){
            for(int j=0;j<pieces[i].length;j++){
                pieces[i][j]= new Piece();
            }
        }

        pieces[heroiRow][heroiColumn].addCharacter(hero);
        pieces[4][9].addCharacter(boss);


        int ramdomRow = ThreadLocalRandom.current().nextInt(1,row);
        int ramdomColumn = ThreadLocalRandom.current().nextInt(1,column);

        for(int i=0;i<elixirs.length;){ //5 numero de elixir
            if(!((ramdomRow == 0 && ramdomColumn == 0) || (ramdomRow == 4 && ramdomColumn == 9)) && pieces[ramdomRow][ramdomColumn].hasPiece()) {
                elixirs[i]= new Elixir();
                pieces[ramdomRow][ramdomColumn].addItems(elixirs[i]);
                ramdomRow = ThreadLocalRandom.current().nextInt(1, row);
                ramdomColumn = ThreadLocalRandom.current().nextInt(1, column);
                i++;
            }
            ramdomRow = ThreadLocalRandom.current().nextInt(1, row);
            ramdomColumn = ThreadLocalRandom.current().nextInt(1, column);
        }


        for(int i=0;i<normalMonster.length;){ //5 numero de Monstros
            if(!((ramdomRow == 0 && ramdomColumn == 0) || (ramdomRow == 4 && ramdomColumn == 9)) && pieces[ramdomRow][ramdomColumn].hasPiece()) {
                normalMonster[i] = new NormalMonster();
                pieces[ramdomRow][ramdomColumn].addCharacter(normalMonster[i]);
                ramdomRow = ThreadLocalRandom.current().nextInt(1, row);
                ramdomColumn = ThreadLocalRandom.current().nextInt(1, column);
                i++;
            }
            ramdomRow = ThreadLocalRandom.current().nextInt(1, row);
            ramdomColumn = ThreadLocalRandom.current().nextInt(1, column);
        }

        for(int i=0;i<trapFixed.length;){ //5 numero de Monstros
            if(!((ramdomRow == 0 && ramdomColumn == 0) || (ramdomRow == 4 && ramdomColumn == 9)) && pieces[ramdomRow][ramdomColumn].hasPiece()) {
                trapFixed[i] = new Fixed();
                pieces[ramdomRow][ramdomColumn].addItems(trapFixed[i]);
                ramdomRow = ThreadLocalRandom.current().nextInt(1, row);
                ramdomColumn = ThreadLocalRandom.current().nextInt(1, column);
                i++;
            }
            ramdomRow = ThreadLocalRandom.current().nextInt(1, row);
            ramdomColumn = ThreadLocalRandom.current().nextInt(1, column);
        }

        for(int i=0;i<trapVariable.length;){ //5 numero de Monstros
            if(!((ramdomRow == 0 && ramdomColumn == 0) || (ramdomRow == 4 && ramdomColumn == 9)) && pieces[ramdomRow][ramdomColumn].hasPiece()) {
                trapVariable[i] = new Variable();
                pieces[ramdomRow][ramdomColumn].addItems(trapVariable[i]);
                ramdomRow = ThreadLocalRandom.current().nextInt(1, row);
                ramdomColumn = ThreadLocalRandom.current().nextInt(1, column);
                i++;
            }
            ramdomRow = ThreadLocalRandom.current().nextInt(1, row);
            ramdomColumn = ThreadLocalRandom.current().nextInt(1, column);
        }
    }

    private void moveHero(KeyEvent e){
        int newRow = heroiRow;
        int newColumn = heroiColumn;

        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                newRow = Math.max(0, heroiRow - 1);
                break;
            case KeyEvent.VK_DOWN:
                newRow = Math.min(row - 1, heroiRow + 1);
                break;
            case KeyEvent.VK_LEFT:
                newColumn = Math.max(0, heroiColumn - 1);
                break;
            case KeyEvent.VK_RIGHT:
                newColumn = Math.min(column - 1, heroiColumn + 1);
                break;
        }

        if (newRow != heroiRow || newColumn != heroiColumn) {
            pieces[heroiRow][heroiColumn].removePiece(hero);
            heroiRow = newRow;
            heroiColumn = newColumn;
            pieces[heroiRow][heroiColumn].addCharacter(hero);
            actions.fight(pieces[heroiRow][heroiColumn]);
            actions.getElixir(pieces[heroiRow][heroiColumn]);
            actions.damageTrap(pieces[heroiRow][heroiColumn]);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                g.drawRect(j * 50, i * 50, 50, 50);
                g.drawString(pieces[i][j].toString(), j * 50 + 20, i * 50 + 30);
            }
        }
    }

    public void printfBoard(){
        for(int i=0;i< pieces.length;i++){
            for(int j=0;j<pieces[i].length;j++){
                pieces[i][j].printPieces();
            }
            System.out.println("|");
        }
    }

    //DEVE SER IMPLEMENTADO NA INTERFACE ACTIONS
//    public void pegaElixir(){
//        Piece currentPiece = pieces[heroiRow][heroiColumn]; //currentPiece = heroi
//
//        if(currentPiece.hasElixir()){
//            messageLabel.setText("Found an elixir!");
//
//        }else if(!currentPiece.hasPiece()){
//            messageLabel.setText("Hero moving!");
//        }
//    }

}
