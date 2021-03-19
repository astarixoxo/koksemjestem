package g56212.luckynumbers.view;

import g56212.luckynumbers.model.Game;
import g56212.luckynumbers.model.Model;

/**
 *
 * @author g56212
 */
public class test {
    public static void main(String[] args) {
        Model model = new Game();
        model.start(2);
        System.out.println(model.getBoardSize());
        MyView view = new MyView(model);
        view.displayWelcome();
        view.displayGame();
    }
}
