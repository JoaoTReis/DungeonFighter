package application;

import boardGame.Board;
import pieces.*;

import javax.swing.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Game Board");
        Board board = new Board();

        Scanner sc = new Scanner(System.in);

        board.initBoard();
        frame.add(board);
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        board.printfBoard();

        sc.close();
    }
}
