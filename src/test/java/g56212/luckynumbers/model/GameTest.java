package g56212.luckynumbers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author MCD <mcodutti@he2b.be>
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /* =====================
         Tests for start()
       ===================== */

 /* --- test related to the state --- */
    @Test
    public void start_when_game_not_started_ok() {
        game.start(4);
    }

    @Test
    public void start_when_game_over_ok() {
        fullPlay();
        game.start(2);
    }

    /* Play a game till the end */
    private void fullPlay() {
        game.start(2);
        int value = 1;
        int line = 0;
        int col = 0;
        for (int turn = 1; turn < game.getBoardSize() * game.getBoardSize(); turn++) {
            for (int player = 0; player < game.getPlayerCount(); player++) {
                game.pickTile(value);
                game.putTile(new Position(line, col));
                game.nextPlayer();
            }
            value++;
            col++;
            if (col == game.getBoardSize()) {
                col = 0;
                line++;
            }
        }
        game.pickTile(20);
        game.putTile(new Position(line, col));
    }

    @Test
    public void start_when_game_in_progress_ISE() {
        game.start(4);
        assertThrows(IllegalStateException.class,
                () -> game.start(1));
    }

    @Test
    public void start_state_changed_to_PICK_TILE() {
        game.start(3);
        assertEquals(State.PICK_TILE, game.getState());
    }

    /* --- tests related to the parameter --- */
    @Test
    public void start_playerCount_too_small_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(1));
    }

    @Test
    public void start_playerCount_minimum_accepted() {
        game.start(2);
    }

    @Test
    public void start_playerCount_maximum_accepted() {
        game.start(4);
    }

    @Test
    public void start_playerCount_too_big_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(5));
    }

    /* -- tests related to fields initialization --- */
    @Test
    public void start_playerCount_initialized() {
        game.start(4);
        assertEquals(4, game.getPlayerCount());
    }

    @Test
    public void start_current_player_is_player_0() {
        game.start(4);
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    @Test
    public void nextPlayer_if_current_player_is_player_0() {
        game.start(2);
        game.pickTile(1);
        game.putTile(new Position(0, 0));
        game.nextPlayer();
        assertEquals(1, game.getCurrentPlayerNumber());
    }

    @Test
    public void nextPlayer_if_current_player_is_player_1() {
        game.start(2);
        game.pickTile(1);
        game.putTile(new Position(0, 0));
        game.nextPlayer();
        game.pickTile(1);
        game.putTile(new Position(0, 0));
        game.nextPlayer();
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    @Test
    public void nextPlayer_when_not_turn_end() {
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer());
    }

    @Test
    public void start_game_when_game_over_not_enough_players() {
        fullPlay();
        assertThrows(IllegalArgumentException.class,
                () -> game.start(1));

    }

    @Test
    public void start_game_when_game_over_too_many_players() {
        fullPlay();
        assertThrows(IllegalArgumentException.class,
                () -> game.start(5));
    }

    @Test
    public void pick_tile_when_place_tile() {
        game.start(2);
        game.pickTile();
        game.putTile(new Position(0, 0));
        assertThrows(IllegalStateException.class,
                () -> game.pickTile());

    }

    @Test
    public void pick_tile_when_not_started() {
        assertThrows(IllegalStateException.class,
                () -> game.pickTile());
    }

    @Test
    public void pick_tile_when_turn_end() {
        game.start(2);
        game.pickTile();
        game.putTile(new Position(0, 0));
        game.nextPlayer();
        assertEquals(new Tile(0), game.pickTile(0));
    }

    @Test
    public void put_tile_when_not_started() {
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(0, 1)));
    }

    @Test
    public void put_tile_when_pick_tile() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(0, 1)));
    }

    @Test
    public void put_tile_when_put_tile() {
        game.start(2);
        game.pickTile(5);
        game.putTile(new Position(0, 0));
        assertEquals(game.getTile(0, new Position(0, 0)), new Tile(5));
    }

    @Test
    public void put_tile_when_turn_end() {
        game.start(2);
        game.pickTile();
        game.putTile(new Position(0, 0));
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(0, 1)));
    }

    @Test
    public void get_winner_when_not_game_over() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.getWinner());
    }

    @Test
    public void get_tile_when_not_started() {
        assertThrows(IllegalStateException.class,
                () -> game.getTile(0, new Position(0, 0)));

    }

    @Test
    public void get_picked_tile_when_not_place_tile() {
        assertThrows(IllegalStateException.class,
                () -> game.getPickedTile());
    }

    @Test
    public void canTileBePut_when_state_not_started() {
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(new Position(0, 0)));
    }

    @Test
    public void canTileBePut_when_not_in_board() {
        game.start(2);
        game.pickTile();
        assertThrows(IllegalArgumentException.class,
                () -> game.canTileBePut(new Position(5, 1)));
    }

    @Test
    public void canTileBePut_when_in_board() {
        game.start(2);
        game.pickTile();
        assertEquals(true, game.canTileBePut(new Position(1, 1)));
    }

    @Test
    public void getPlayerCount_when_is_not_started() {
        assertThrows(IllegalStateException.class,
                () -> game.getPlayerCount());
    }

    @Test
    public void getPlayerCount_when_isnt_not_started() {
        game.start(2);
        assertEquals(2, game.getPlayerCount());
    }

    /* === À vous de compléter... === */
}
