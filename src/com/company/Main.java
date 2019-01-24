package com.company;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Game Saper
 * The game consists in marking boxes without bombs. Each digit in the game means the number of bombs around it.
 * @author Arkadiusz Kubiak
 * @version 1.0
 * @since 23.01.2018
 *
 */

public class Main {
    static Scanner scanner = new Scanner(System.in);

    /**
     * This is main method. Sorts the methods needed to play the game.
     */
    public static void main(String[] args) {
        hello();
        Data.table();
        Data.table2();
        Data.subtraction1();
        Data.changingFieldInGame();
        Data.fieldInGame();
        game();
    }

    /**
     * The method welcomes the user and retrieves the size of the playing field
     */
    public static void hello() {
        System.out.println("Podaj swoje imię:");
        Data.name = scanner.nextLine();
        System.out.println("Witaj " + Data.name + " w grze Saper autorstwa Arkadiusza Kubiak.");
        System.out.println("Podaj szerokość pola gry: ");
        try {
            Data.column = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Podałeś nieprawidłową liczbę!");
            scanner.close();
            end();
        }

        System.out.println("Podaj wysokość pola gry: ");
        try {
            Data.line = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Podałeś nieprawidłową liczbę!");
            scanner.close();
            end();
        }
    }

    /**
     * The method asks the player which column should be marked as a bomb or uncovered
     */
    public static void whatDoYouWant(){
        System.out.println("Wpisz teraz numer kolumny (od 1 do " +Data.column +"), a następnie numer wiersza (od 1 do " +Data.line + ")");
        try {
        Data.chooseColumn = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Podałeś nieprawidłową liczbę!");
            end();
        }
        try {
        Data.chooseLine = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Podałeś nieprawidłową liczbę!");
            end();
        }

        if (Data.chooseColumn>Data.column) {
            System.out.println("Błędny numer!!");
            whatDoYouWant();
        }
        if (Data.chooseLine>Data.line) {
                System.out.println("Błędny numer!!");
                whatDoYouWant();
            }
        else {
            System.out.println("Wpisz 'o' jeśli chcesz odkryć dane pole lub '*' jeśli chcesz oznaczyć pole jako bombę.");
            try {
            Data.choose = scanner.next().charAt(0);
            } catch (InputMismatchException e) {
                System.out.println("Podałeś nieprawidłowy znak!");
                end();
            }
        }
    }

    /**
     * The method sorts the remaining methods for the proper operation of the game. With the help of the loop, the game goes on until "lose" is 0.
     */
    public static void game() {
        for(int i=0; Data.lose==0; i++) {
            whatDoYouWant();
            Data.doChoose();
            Data.fieldInGame();
            isUserWin();
            if (Data.lose == 1) {
                System.out.println("Przegrałeś!");
            }
            if (Data.lose == 2) {
                System.out.println("Gratulacje. Wygrałeś!");
            }
        }
    }

    /**
     * The method checks if the user has already won. Counts the uncovered fields and fields without bombs.
     * @param count - Counts fields without bombs.
     * @param count2 - Counts uncovered fields.
     */
    public static void isUserWin(){
        int count=0;
        int count2=0;

        for(int i=1; i<Data.table.length-1; i++) {
            for (int j = 1; j < Data.table[i].length-1; j++)
                if (Data.table[i][j] == 0) {
                    count++;
                }
        }
        for(int i=0; i<Data.table2.length-1; i++) {
            for (int j = 0; j < Data.table2[i].length-1; j++)
                if (Data.table2[i][j] == 88) {
                    count2++;
                }
        }
        if (count==count2){
            Data.lose = 2;
        }
        }
        public static void end(){
        System.exit(0);
        }
    }


