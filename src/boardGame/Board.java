package boardGame;

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
    private int heroiRow =0;
    private int heroiColumn = 0;

    //Pecas no tabueiro
    private Object hero=new Heroes().decideHeroi();
    private Object boss= new Boss();
    private Object[] elixirs = new Elixir[5];
    private Object[] normalMonster = new NormalMonster[5];
    private Object[] trapVariable = new Variable[5];
    private Object[] trapFixed = new Fixed[5];

    //tabuleiro
    private Piece[][] pieces = new Piece[row][column];

    private JLabel messageLabel;

    public Board(){
        for(int i=0;i< pieces.length;i++){
            for(int j=0;j<pieces[i].length;j++){
                pieces[i][j]= new Piece();
            }
        }

        messageLabel =new JLabel("Use the arrow keys to move."); // Adiciona um JLabel para mensagens
        this.add(messageLabel, BorderLayout.SOUTH);

        // Adiciona o KeyListener para capturar as teclas
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moveHero(e);
                pegaElixir();
                repaint();
            }
        });
    }

    public void initBoard(){


        pieces[heroiRow][heroiColumn].addHero(hero);
        pieces[4][9].addBoss(boss);


        int ramdomRow = ThreadLocalRandom.current().nextInt(1,row);
        int ramdomColumn = ThreadLocalRandom.current().nextInt(1,column);

        for(int i=0;i<elixirs.length;){ //5 numero de elixir
            if(!((ramdomRow == 0 && ramdomColumn == 0) || (ramdomRow == 4 && ramdomColumn == 9)) && pieces[ramdomRow][ramdomColumn].hasPiece()) {
                elixirs[i]= new Elixir();
                pieces[ramdomRow][ramdomColumn].addElixir(elixirs[i]);
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
                pieces[ramdomRow][ramdomColumn].addNormalMonster(normalMonster[i]);
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
                pieces[ramdomRow][ramdomColumn].addTrapFixed(trapFixed[i]);
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
                pieces[ramdomRow][ramdomColumn].addTrapVariable(trapVariable[i]);
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
            pieces[heroiRow][heroiColumn].addHero(hero);
            pegaElixir();
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

    public void pegaElixir(){
        Piece currentPiece = pieces[heroiRow][heroiColumn]; //currentPiece = heroi

        if(currentPiece.hasElixir()){
            messageLabel.setText("Found an elixir!");
            
        }else if(!currentPiece.hasPiece()){
            messageLabel.setText("Hero moving!");
        }
    }

}
