package g56212.luckynumbers.model;

import static g56212.luckynumbers.model.State.*;

/**
 *
 * @author piotr
 */
public class Game implements Model {

    private State state;
    private int playerCount;
    private int currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;

    public Game() {
        this.state = NOT_STARTED;
    }

    public void start(int playerCount) {
        if (state != NOT_STARTED && state != GAME_OVER) {
            throw new IllegalStateException("");
        }
        if (playerCount < 2 || playerCount > 4) {
            throw new IllegalArgumentException("");
        }
        state = PICK_TILE;
        this.currentPlayerNumber = 0;
        this.playerCount = playerCount;
        this.boards = new Board[playerCount];
        for (int i = 0; i < playerCount; i++) {
            boards[i] = new Board();
        }
    }

    @Override
    public Tile pickTile() {
        if (state != PICK_TILE) {
            throw new IllegalStateException("State must be PICK_TILE, but"
                    + " actual state is: " + state);
        }
        this.state = PLACE_TILE;
        return this.pickedTile = new Tile((int) (Math.random() * 20 - 1) + 1);
    }

    Tile pickTile(int value) {
        if (state != PICK_TILE) {
            throw new IllegalStateException("");
        }
        this.state = PLACE_TILE;
        this.pickedTile = new Tile(value);
        return this.pickedTile;
    }

    @Override
    public int getBoardSize() {

        return boards[currentPlayerNumber].getSize();
    }

    @Override
    public void putTile(Position pos) {
        if (this.state != PLACE_TILE) {
            throw new IllegalStateException(" ");
        }
        if (!canTileBePut(pos)) {
            throw new IllegalArgumentException("");
        }
        boards[currentPlayerNumber].put(pickedTile, pos);
        if (boards[currentPlayerNumber].isFull()) {
            this.state = GAME_OVER;
        } else {
            this.state = TURN_END;
        }
    }

    @Override
    public void nextPlayer() {
        if (state != TURN_END) {
            throw new IllegalStateException("State must be TURN_END, but"
                    + " actual state is: " + state);
        }
        state = PICK_TILE;
        if (this.currentPlayerNumber >= this.playerCount - 1){
            this.currentPlayerNumber = 0;
        } else {
            this.currentPlayerNumber = this.currentPlayerNumber + 1;
        }
    }

    @Override
    public int getPlayerCount() {
        if (this.state == NOT_STARTED) {
            throw new IllegalStateException("State can't be NOT_STARTED");
        }

        return this.playerCount;
    }

    @Override
    public State getState() {

        return this.state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (state == NOT_STARTED || state == GAME_OVER) {
            throw new IllegalStateException("State can't be NOT_STARTED "
                    + "OR GAME_OVER");
        }
        return currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (state != PLACE_TILE) {
            throw new IllegalStateException("State must be PLACE_TILE "
                    + "but actual state is: "+state);
        }
        return pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        return boards[currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (state != PLACE_TILE) {
            throw new IllegalStateException("State must PLACE_TILE, but"
                    + "actual state is:  "+state);
        }
        if (!boards[currentPlayerNumber].isInside(pos)) {
            throw new IllegalArgumentException("Given position"
                    + " isn't inside the board");
        }

        return boards[currentPlayerNumber].canBePut(pickedTile, pos);
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (state == NOT_STARTED) {
            throw new IllegalStateException("State cannot be NOT_STARTED");
        }
        if (!isInside(pos) || playerNumber > playerCount || playerNumber < 0) {
            throw new IllegalArgumentException("Given position isn't inside "
                    + "the board or given player number isn't between 2 and 4");
        }

        return boards[playerNumber].getTile(pos);
    }

    @Override
    public int getWinner() {
        if (state != GAME_OVER) {
            throw new IllegalStateException("");
        }

        return currentPlayerNumber;
    }
}
