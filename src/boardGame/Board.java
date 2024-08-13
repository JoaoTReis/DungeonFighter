package boardGame;
//tem a decaracao de linhas e colunas para determinar tamanho, position é a posicao no tabuleiro
public class Board {
    private final int rows;
    private final int colums;
    private Piece[][] pieces;


    public Board() {
        rows = 5;
        colums = 10;
        pieces = new Piece[rows][colums];
    }

    public int getRows() {
        return rows;
    }

    public int getColums() {
        return colums;
    }

    public Piece piece(int row,int column){
        return pieces[row][column];
    }

}
