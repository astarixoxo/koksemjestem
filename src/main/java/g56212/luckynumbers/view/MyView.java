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

    private void displayUpperFrame() {
        System.out.print("  ");
        for (int i = 1; i <= 4; i++) {
            System.out.print("  " + i);
        }
        System.out.println("  ");
        System.out.print("  ");
        for (int i = 0; i < 12; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    private void displayBoard() {
        for (int i = 0; i < model.getBoardSize(); i++) {
            System.out.print(i + 1 + " |");
            for (int j = 0; j < model.getBoardSize(); j++) {
                Position pos = new Position(i, j);
                if (model.getTile(model.getCurrentPlayerNumber(), pos) == null) {
                    System.out.printf("%2s ", ".");
                } else {
                    System.out.printf("%2s ", model.getTile(model.getCurrentPlayerNumber(), pos).getValue());
                }

            }
            System.out.println("");
        }
    }

    @Override
    public void displayGame() {
        System.out.println("Board of player n° " + model.getCurrentPlayerNumber());
        displayUpperFrame();
        displayBoard();
    }


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


    @Override
    public int askPlayerCount() {
        return readIntBetweenRange("How many players? (Between 2 and 4 players allowed)", 2, 4);
    }

    private int readRow(String message) {
        return readIntBetweenRange(message, 0, model.getBoardSize()) - 1;

    }

    private int readCol(String message) {
        return readIntBetweenRange(message, 0, model.getBoardSize()) - 1;
    }


    @Override
    public Position askPosition() {
        System.out.println("Where would you put the next tile: "+ model.getPickedTile() +"?");
        Position pos;
        int col = readCol("Insert the column");
        int row = readRow("Insert the row");
        pos = new Position(row, col);
        while (!model.isInside(pos) || !model.canTileBePut(pos)) {
            System.out.println("Where would you put the next tile: "+ model.getPickedTile() +"?");
            col = readCol("Insert the column");
            row = readRow("Insert the row");

            pos = new Position(row, col);
        }

        return pos;
    }


    @Override
    public void displayError(String message) {
        System.err.println(message);
    }

}
