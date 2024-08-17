package application;

import boardGame.Board;
import pieces.*;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

//        Board board = new Board();

        Scanner sc = new Scanner(System.in);

        char decisao = 0;
        do {
        System.out.print("choose your hero:");
        int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Warrior warrior = new Warrior();
                    System.out.println(warrior);
                    System.out.println("Deseja realmente utilizar esta classe S/n?");
                    decisao = sc.next().charAt(0);
                    if (decisao == 's') {
                        warrior.distribuiPontos();
                    }
                    break;
                case 2:
                    Paladin paladin = new Paladin();
                    System.out.println(paladin);
                    System.out.println("Deseja realmente utilizar esta classe S/n?");
                    decisao = sc.next().charAt(0);
                    if (decisao == 's') {
                        paladin.distribuiPontos();
                    }
                    break;
                case 3:
                    Barbarian barbarian = new Barbarian();
                    System.out.println(barbarian);
                    System.out.println("Deseja realmente utilizar esta classe S/n?");
                    decisao = sc.next().charAt(0);
                    if (decisao == 's') {
                        barbarian.distribuiPontos();
                    }
                    break;
                default:
            }
        }while(decisao == 'n');

        sc.close();
    }
}
