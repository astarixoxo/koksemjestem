package g56212.luckynumbers;

import g56212.luckynumbers.controller.Controller;
import g56212.luckynumbers.model.*;
import g56212.luckynumbers.view.*;

/**
 *
 * @author g56212
 */
public class LuckyNumbers {

    public static void main(String[] args) {
        Model game = new Game(); 
        Controller controller = new Controller(game, new MyView(game));
        controller.play();
    }
}
