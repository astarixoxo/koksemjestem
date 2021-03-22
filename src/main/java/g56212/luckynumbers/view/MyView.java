/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56212.luckynumbers.view;

import g56212.luckynumbers.model.*;
import java.util.Scanner;

/**
 *
 * @author piotr
 */
public class MyView implements View {

    private Model model;
//*

    public MyView(Model model) {
        this.model = model;
    }

    @Override
    public void displayWelcome() {
        System.out.println("| Lucky Numbers 56212  V1.0 |");
        System.out.println("|=============================|");
    }

    @Override
    public void displayGame() {
        System.out.print("  ");
        for (int i = 1; i <= 4; i++) {
            System.out.print(" " + i);
        }
        System.out.println("");
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print("-");
        }
        System.out.println("");
        for (int i = 0; i < model.getBoardSize(); i++) {
            System.out.print(i + 1 + " |");
            for (int j = 0; j < model.getBoardSize(); j++) {
                Position pos = new Position(i,j);
                if(model.getTile(model.getCurrentPlayerNumber(), pos)==null){
                    System.out.print(" .");
                }
                else{
                System.out.print(" "+model.getTile
        (model.getCurrentPlayerNumber(), pos));
                }
                
            }
            System.out.println("");
        }
        System.out.println(model.getCurrentPlayerNumber());
    }

    /**
     * ¨Displays the current player that have attained the end of the game, it
     * means that the winner is printed.
     */
    @Override
    public void displayWinner() {
        System.out.println(model.getWinner()
                + " is the winner of the game! Nice one!");
    }

    /**
     * Reads the keyboard's entry and meanwhile verifies if it's the attended
     * value In this case methods verifies if it's an integer, if it's the case
     * it returns the value of the user.
     *
     * @param message message to print in this
     * @return keyboard entry, integer in this case.
     */
    private int readInt(String message) {
        Scanner kbd = new Scanner(System.in);
        System.out.println(message);
        while (!kbd.hasNextInt()) {
            kbd.next();
            displayError("Insert the number again!");
            System.out.println(message);
        }
        return kbd.nextInt();
    }

    /**
     * Reads an number (integer) between a range designated by the tab size
     *
     * @param message message to print to the user.
     * @param min Smallest value in the tab, always 0.
     * @param max Length of the game board ( model.getBoardSize() )
     * @return
     */
    private int readIntBetweenRange(String message, int min, int max) {
        int number = readInt(message);
        while (number < min || number > max) {
            displayError("The number isn't between "
                    + min + " and " + max);
            number = readInt(message);
        }
        return number;
    }

    /**
     * Method used to call an another method to read the entry from the keyboard
     * between range, given in the parameters(always 2,4).
     *
     * @return Inserted integer by the user between 2 and 4.
     */
    @Override
    public int askPlayerCount() {
        return readIntBetweenRange
        ("How many players? (Between 2 and 4 players allowed", 2, 4);
    }

    private int readRow(String message) {
        return readIntBetweenRange(message, 0, model.getBoardSize());

    }

    private int readCol(String message) {
        return readIntBetweenRange(message, 0, model.getBoardSize());
    }

    /**
     * Demands to the user to insert a position, the col, then the row. It
     * allows only to create an position that isn't already occupied or out of
     * the array
     *
     * @return position created by the user.
     */
    @Override
    public Position askPosition() {
        System.out.println("Where would you put your tile?");
        Position pos;
        int col = readCol("Insert the column");
        int row = readRow("Insert the row");
        pos = new Position(row, col);
        while (!model.isInside(pos) || !model.canTileBePut(pos)) {
            System.out.println("Where would you put your tile?");
            col =readCol("Insert the column");
            row = readRow("Insert the row");
      
            pos = new Position(row, col);
        }

        return pos;
    }

    /**
     * Displays the String given as the parameter in the console, as an error.
     *
     * @param message the error to display.
     */

    @Override
    public void displayError(String message) {
        System.err.println(message);
    }

}
