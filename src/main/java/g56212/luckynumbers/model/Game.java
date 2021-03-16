/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56212.luckynumbers.model;

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
        this.state = State.NOT_STARTED;
    }

    @Override
    public void start(int playerCount) {

        this.playerCount = playerCount;
        this.currentPlayerNumber = 0;
        this.boards = new Board[playerCount];

        if (playerCount < 2 || playerCount > 4) {
            throw new IllegalArgumentException("Incorrect number of players.");
        }
        if (this.state != State.NOT_STARTED
                && this.state != State.GAME_OVER) {
            throw new IllegalStateException("state is not NOT_STARTED nor" + " GAME_OVER.");
        }

        for (int i = 0; i < playerCount; i++) {
            this.boards[i] = new Board();
        }
        this.state = State.PICK_TILE;

    }

    @Override
    public int getBoardSize() {
        return this.boards.length;
    }

    public Tile pickTile(int value) {
        if (this.state != State.PICK_TILE) {
            throw new IllegalStateException("STATE IS NOT PICK TILE");
        }
        this.state = State.PLACE_TILE;
        return new Tile(value);
    }

    @Override
    public Tile pickTile() {
        if (this.state != State.PICK_TILE) {
            throw new IllegalArgumentException("STATE IS NOT PICK_TILE");
        }
        this.state = State.PLACE_TILE;
        return pickedTile = new Tile((int) (Math.random() * 20) + 1);
    }

    @Override
    public void putTile(Position pos) {
        if (this.state != State.PLACE_TILE) {
            throw new IllegalStateException("STATE IS NOT PLACE TILE");
        }

        if (this.boards[this.currentPlayerNumber].isInside(pos)
                && this.boards[this.currentPlayerNumber].canBePut(this.pickedTile, pos)) {
            this.boards[this.currentPlayerNumber].put(this.pickedTile, pos);
            this.state = State.TURN_END;
        } else {
            throw new IllegalArgumentException("position outside of the board or position not allowed by the rules");
        }
        if (this.boards[this.currentPlayerNumber].isFull()) {
            this.state = State.GAME_OVER;
        }

    }

    @Override
    public void nextPlayer() {
        if (this.state != State.TURN_END) {
            throw new IllegalStateException("STATE IS NOT TURN END");
        }
        this.currentPlayerNumber++;
        if (this.currentPlayerNumber >= this.playerCount) {
            this.currentPlayerNumber = 0;
        }

    }

    @Override
    public int getPlayerCount() {
        if (this.state != State.NOT_STARTED) {
            throw new IllegalStateException("STATE IS NOT NOT_STARTED JH");
        }
        return this.playerCount;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (this.state != State.NOT_STARTED && this.state != State.GAME_OVER) {
            throw new IllegalStateException("STATE IS NOT NOT_STARTED OR GAME_OVER");
        }
        return this.currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (this.state != State.PLACE_TILE) {
            throw new IllegalStateException("STATE IS NOT PLACE_TILE");
        }
        return this.pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        return this.boards[this.currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (!isInside(pos)) {
            throw new IllegalArgumentException("Position is outside the board");
        }
        if (this.state != State.PLACE_TILE) {
            throw new IllegalStateException("STATE IS NOT PLACE_TILE");
        }
        return this.boards[this.currentPlayerNumber].canBePut(this.pickedTile, pos);
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (!isInside(pos)) {
            throw new IllegalArgumentException("Position is outside the board");
        }
        if (this.state != State.NOT_STARTED) {
            throw new IllegalStateException("STATE IS NOT NOT_STARTED ER");
        }
        return this.boards[playerNumber].getTile(pos);
    }

    @Override
    public int getWinner() {
        if (this.state != State.GAME_OVER) {
            throw new IllegalStateException("STATE IS NOT GAME_OVER");
        }
        return this.currentPlayerNumber;
    }

    public static void main(String[] args) {

    }
}
