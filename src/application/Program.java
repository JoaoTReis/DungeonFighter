package application;

import boardGame.Board;
import pieces.*;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Board board = new Board();

        Scanner sc = new Scanner(System.in);

        board.initBoard();
        board.printfBoard();

        sc.close();
    }
}
