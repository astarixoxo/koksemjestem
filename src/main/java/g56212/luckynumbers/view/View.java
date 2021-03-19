package g56212.luckynumbers.view;

import g56212.luckynumbers.model.Position;

/**
 *
 * @author piotr
 */
public interface View {

    void displayWelcome();
    void displayGame();
    void displayWinner();
    int askPlayerCount();
    Position askPosition();
    void displayError(String message);
    
}
