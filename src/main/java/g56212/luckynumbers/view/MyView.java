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

    public MyView(Model model) {
        this.model = new Game();
    }
    public int elo(){
    return model.getBoardSize();
    }

    @Override
    public void displayWelcome() {
        System.out.println("| Lucky Numbers 56212  V1.0 |");
        System.out.println("|=============================|");
    }

    @Override
    public void displayGame() {
        for (int i = 0; i < model.getBoardSize(); i++) {
            for (int j = 0; j < model.getBoardSize() - 1; j++) {
  
                    System.out.print(". ");
                
            }
            System.out.println("");
        }
    }

    @Override
    public void displayWinner() {
        System.out.println(model.getWinner() + " is the winner of the game! Nice one!");
    }

    private int readInt(String message) {
        Scanner kbd = new Scanner(System.in);
        System.out.println("How many players? (Between 2 and 4 players allowed)");
        while (!kbd.hasNextInt()) {
            kbd.next();
            displayError("Number of players must be between 2 and 4 players, try again!");
            System.out.println("How many players? (Between 2 and 4 players allowed)");
        }
        return kbd.nextInt();
    }

    private int readIntBetweenRange(String message, int min, int max) {
        int number = readInt(message);
        while (number < min || number > max) {
            displayError("The number isn't between " + min + " and " + max + " players");
            number = readInt(message);
        }
        return number;
    }

    @Override
    public int askPlayerCount() {
        return readIntBetweenRange("How many players? (Between 2 and 4 players allowed", 2, 4);
    }

    private int readRow(String message) {
        return readIntBetweenRange(message, 0, model.getBoardSize());

    }

    private int readCol(String message) {
        return readIntBetweenRange(message, 0, model.getBoardSize());
    }

    @Override
    public Position askPosition() {
        System.out.println("Where would you put your tile?");
        Position pos;
        int col = readCol("Insert the row");
        int row = readRow("Insert the row");
        pos = new Position(row, col);
        while (!model.isInside(pos)) {
            System.out.println("Where would you put your tile?");
            readRow("Insert the row");
            readCol("Insert the column");
            pos = new Position(row, col);
        }
        return pos;
    }

    @Override
    public void displayError(String message) {
        System.err.println(message);
    }

}
