package boardGame;
//retorna a posicao no tabuleiro
public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int setRow(int row) {
        this.row = row;
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int setColumn(int column) {
        this.column = column;
        return column;
    }
}
