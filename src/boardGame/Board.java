package boardGame;

import pieces.Characters;
import pieces.Heroes;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

//tem a decaracao de linhas e colunas para determinar tamanho, position é a posicao no tabuleiro
public class Board {
    private int row = 5;
    private int column = 10;

    private Piece[][] pieces = new Piece[row][column];

    public void initBoard(){
        int ramdomRow = ThreadLocalRandom.current().nextInt(1,row);
        int ramdomColumn = ThreadLocalRandom.current().nextInt(1,column);

        for(int i=0;i< pieces.length;i++){
            for(int j=0;j<pieces[i].length;j++){
                pieces[i][j]= new Piece();

                if(i ==0 && j==0) {
                    pieces[i][j].addCharacters();
                }
            }
        }

        for(int i=0;i<5;i++){ //5 numero de elixir
            if(!((ramdomRow == 0 && ramdomColumn == 0) || (ramdomRow == 4 && ramdomColumn == 9))) {
                pieces[ramdomRow][ramdomColumn].addElixir();
                ramdomRow = ThreadLocalRandom.current().nextInt(1, row);
                ramdomColumn = ThreadLocalRandom.current().nextInt(1, column);
            }
        }
    }

    public void printfBoard(){
        for(int i=0;i< pieces.length;i++){
            for(int j=0;j<pieces[i].length;j++){
                pieces[i][j].printPieces();
                //System.out.printf("%-4s", " ");
            }
            System.out.println("|");
        }
    }
}
