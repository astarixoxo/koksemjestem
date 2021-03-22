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
        if (state != State.NOT_STARTED && state != State.GAME_OVER) {
            throw new IllegalStateException("State is not NOT_STARTED or GAME_OVER, state is: "+ state);

        }
        if (playerCount < 2 || 4 < playerCount) {
            throw new IllegalArgumentException("Number of players is not between 2 and 4 (both included)");
        }
        boards = new Board[playerCount];
        for (int i = 0; i < playerCount; i++) {
            boards[i] = new Board();
        }
        this.playerCount = playerCount;
        currentPlayerNumber = 0;
        state = State.PICK_TILE;
    }

    @Override
    public int getBoardSize() {
        return boards.length;
    }

    public int pickTile(int value) {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException("State is not PICK_TILE");
        }
        state = State.PLACE_TILE;
        return value;
    }

    public Tile pickTile() {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException("State is not PICK_TILE");
        }
        state = State.PLACE_TILE;
        return pickedTile = new Tile((int) (Math.random() * 20 - 1) + 1);
    }

    @Override
    public void putTile(Position pos) {
        if (state != State.PLACE_TILE) {
            throw new IllegalStateException("State is not PLACE_TILE");
        }
        if (!boards[currentPlayerNumber].isInside(pos)
                || !boards[currentPlayerNumber].canBePut(pickedTile, pos)) {
            throw new IllegalArgumentException("position outside of the board, or position not allowed by the rules");
        }
        boards[currentPlayerNumber].put(pickedTile, pos);
        state = State.TURN_END;
        if (boards[currentPlayerNumber].isFull()) {
            state = State.GAME_OVER;
        }
    }

    @Override
    public void nextPlayer() {
        if (state != State.TURN_END) {
            throw new IllegalStateException("State is not TURN_END, state is: " + state);
        }
        this.state=State.PICK_TILE;
        currentPlayerNumber++;
        if (currentPlayerNumber >= playerCount) {
            currentPlayerNumber = 0;
        }
    }

    @Override
    public int getPlayerCount() {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("State is not NOT_STARTED");
        }
        return playerCount;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (state == State.NOT_STARTED && state == State.GAME_OVER) {
            throw new IllegalStateException("State is not NOT_STARTED OR GAME_OVER");
        }
        return currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (state != State.PLACE_TILE) {
            throw new IllegalStateException("State is not PLACE_TILE ");
        }
        return pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        return boards[currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {

        if (state != State.PLACE_TILE) {
            throw new IllegalStateException("State is not PLACE_TILE");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("Position outside of the board");
        }
        return boards[currentPlayerNumber].canBePut(pickedTile, pos);
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("State is NOT_STARTED");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("Position outside of the board");
        }
        return boards[playerNumber].getTile(pos);
    }

    @Override
    public int getWinner() {
        if (state != State.GAME_OVER) {
            throw new IllegalStateException("State is not GAME_OVER");
        }
        return currentPlayerNumber;
    }

}
