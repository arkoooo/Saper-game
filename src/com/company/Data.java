package com.company;
import java.util.Random;

/**
 * The class stores the data needed for the game
 * @param column - Width of gamefield.
 * @param line - Length of gamefield.
 * @param name - Stores name of player.
 * @param table - Field of mines.
 * @param table2 - A field containing the number of bombs around. It's a main gamefield.
 * @param random - Randomization parameter from class Random.
 * @param chooseColumn - Column choosed by user.
 * @param chooseLine - Line choosed by user.
 * @param choose - Saves user choose. '*' or 'o'.
 * @param lose - This variable contains information about user lost.
 *
 */
public class Data {
    static int column;
    static int line;
    static String name;
    static int[][] table;
    static int[][] table2;
    static Random random = new Random();
    static int chooseColumn;
    static int chooseLine;
    static char choose;
    static int lose=0;

    /**
     * This method create's array of bombs.
     * @return table
     */
    public static int[][] table() {
        table = new int[line + 2][column + 2];
        for (int i = 1; i < column + 1; i++)
            for (int j = 1; j < line + 1; j++)
                table[j][i] = random.nextInt(2);
        return Data.table;
    }

    /**
     * This method shows array of bombs 'table'. It can be turned on if we want to cheat.
     * @return table.
     */
    public static int[][] field() {
        System.out.println("Tablica bomb:\n");
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++)
                System.out.print(table[i][j] + "  ");
            System.out.println();
        }
        return table;
    }

    /**
     * This method creates an array of the "table" array size. All indexex are filled by 0.
     * @return table2.
     */
    public static int[][] table2() {
        table2 = new int[line+2][column+2];
        for (int i = 0; i < table2.length; i++)
            for (int j = 0; j < table2[i].length; j++)
                table2[i][j] = 0;
        System.out.println();
        return table2;
    }

    /**
     * This method shows array "table2". It's main gamefield. Every number it's amount of bombs around. If any field is "88" it shows 'o', if its '99' it shows "8".
     * @return table2.
     */
    public static int[][] fieldInGame() {
        System.out.println("Pole gry:\n");
        for (int i = 1; i < table2.length-1; i++) {
            for (int j = 1; j < table2[i].length - 1; j++)
                switch (table2[i][j]) {
                    case 88:
                        System.out.print('o' + "   ");
                        break;
                    case 99:
                        System.out.print('*' + "   ");
                        break;
                    default:
                        System.out.print(table2[i][j] + "   ");
                }
            System.out.println();
            }
        return table2;
    }

    /**
     * This method checks every index in array with bombs. If any field is bombed, then the same field in "table2" is increased by 1.
     * @param c - C like Column. It's used like counter.
     * @param l - L like Line. It's used like counter.
     */
    public static void changingFieldInGame() {
        int c=1;
        int l=1;

        for (c=1; c<column+1; c++){
            for (l=1; l<line+1; l++)
                 for (int i=l-1; i<l+2; i++)
                    for(int j=c-1; j<c+2; j++)
                         if (table[i][j] == 1) {
                             table2[l][c] = table2[l][c] + 1;
                         }
        }
    }

    /**
     * This method decreases field by 1 if field is bombed.
     */
    public static void subtraction1 () {
        for (int i = 0; i < table.length; i++)
            for (int j = 0; j < table[i].length; j++)
                if (table[i][j] == 1)
                    table2[i][j] = table2[i][j] - 1;
    }

    /**
     * This method executes user's choose. If user choose 'o', checks field of bombs. If field is bombed, then user loses. If not it changes field to '88'. If user choose 'o' field is changed to '99'.
     */
    public static void doChoose(){
        switch (choose){
            case 'o':
                if (table[chooseLine][chooseColumn] == 1){
                    lose++;
                }
                else{
                    table2[chooseLine][chooseColumn] = 88;
                }
                break;
            case '*':
                table2[chooseLine][chooseColumn] = 99;
                break;
            default:
                System.out.println("Zły znak, spróbuj jescze raz.");
                Main.game();
        }
    }
}








