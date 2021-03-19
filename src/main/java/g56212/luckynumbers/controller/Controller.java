package g56212.luckynumbers.controller;

import g56212.luckynumbers.view.*;
import g56212.luckynumbers.model.*;

/**
 *
 * @author g56212
 */
public class Controller {

    private Model game;
    private MyView view;

    public Controller(Model model, MyView view) {
        this.game = model;
        this.view = view;
    }

    public void play() {
        view.displayWelcome();
        boolean elo = true;
        while (elo) {
            switch (game.getState()) {
                case NOT_STARTED:
                    
                    int playerCount = view.askPlayerCount();
                    
                    game.start(playerCount);

                    break;
                case PICK_TILE:
                    game.pickTile();
                    break;
                case PLACE_TILE:
                    game.putTile(view.askPosition());

                    break;
                case TURN_END:
                    game.nextPlayer();
                    break;
                case GAME_OVER:
                    view.displayWinner();
                    game.start(view.askPlayerCount());
                    break;

            }

        }
    }
}
