package application;

import pieces.*;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("choose your hero:");
        int choice = sc.nextInt();
        Heroes hero = new Heroes(choice).selectHero();


        if (hero instanceof Warrior) {
            Warrior warrior = (Warrior) hero;
            warrior.ability(); // Método específico de Warrior
        } else if (hero instanceof Paladin) {
            Paladin paladin = (Paladin) hero;
            paladin.ability(); // Método específico de Paladin
        } else if (hero instanceof Barbarian) {
            Barbarian barbarian = (Barbarian) hero;
            barbarian.ability(); // Método específico de Barbarian
        }

        System.out.println(hero);
        sc.close();
    }
}
