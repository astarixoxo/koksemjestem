package g56212.luckynumbers.view;

import g56212.luckynumbers.model.Position;

/**
 *
 * @author piotr
 */
public interface View {
    /**
     * Displays the information about the name of the game, and also about the
     * version and it's author.
     */
    void displayWelcome();
    /**
     * Displays the board of the current player that is made up from a header
     * and it's body.
     */
    void displayGame();

    /**
     * ¨Displays the current player that have attained the end of the game, it
     * means that the winner is printed.
     */
    void displayWinner();

    /**
     * Method used to call an another method to read the entry from the keyboard
     * between range, given in the parameters(always 2,4).
     *
     * @return Inserted integer by the user between 2 and 4.
     */
    int askPlayerCount();

    /**
     * Demands to the user to insert a position, the col, then the row. It
     * allows only to create an position that isn't already occupied or out of
     * the array
     *
     * @return position created by the user.
     */
    Position askPosition();

    /**
     * Displays the String given as the parameter in the console, as an error.
     *
     * @param message the error to display.
     */

    void displayError(String message);

}
