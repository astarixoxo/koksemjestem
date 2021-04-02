package g56212.luckynumbers.model;

/**
 *
 * @author piotr
 */
public class Board {

    private Tile[][] tiles;

    /**
     * Initialises an object(board of tiles), by default it's a squared 2D
     * array by size of 4.
     */
    public Board() {
        this.tiles = new Tile[4][4];
    }

    /**
     * Getter of this.tiles
     *
     * @return
     */
    public Tile[][] getTile() {
        return tiles;
    }

    /**
     * Getter of the size of actual Tiles 2D Array of n*n size
     *
     * @return size of the
     */
    public int getSize() {
        return tiles.length;
    }

    /**
     * Verifies if the given position is inside of the array or not.
     *
     * @param pos position that must be verified.
     * @return true if i
     */
    public boolean isInside(Position pos) {
        return ((pos.getColumn() >= 0
                && pos.getColumn() < this.getSize())
                && (pos.getRow() >= 0
                && pos.getRow() < this.getSize()));
    }

    /**
     * Getter of the tile at the given position
     *
     * @param pos position that will be used to acces the tile at the given
     * position.
     * @return Returns the tile at the given position.
     */
    public Tile getTile(Position pos) {
        return tiles[pos.getRow()][pos.getColumn()];
    }

    /**
     * Verifies if the given tile can be putted in to given position. The rule
     * is that the values above and to the left must be smaller than the value
     * of the specified position, the values to the right and to the bottom must
     * be larger.
     *
     * @param tile The tile that will be used to verify if it can be inserted or
     * not.
     * @param pos Position that will be used to verify if the given tile may be
     * inserted in this position or not.
     * @return
     */
    public boolean canBePut(Tile tile, Position pos) {
        for (int i = 1; i < getSize(); i++) {
            if (pos.getColumn() - i >= 0) {
                if (tiles[pos.getRow()][pos.getColumn() - i] != null) {
                    if (tiles[pos.getRow()][pos.getColumn() - i].getValue()
                            >= tile.getValue()) {
                        return false;
                    }
                }
            }

            if (pos.getColumn() + i < getSize()) {
                if (tiles[pos.getRow()][pos.getColumn() + i] != null) {
                    if (tiles[pos.getRow()][pos.getColumn() + i].getValue()
                            <= tile.getValue()) {
                        return false;
                    }
                }
            }
            if (pos.getRow() - i >= 0) {
                if (tiles[pos.getRow() - i][pos.getColumn()] != null) {
                    if (tiles[pos.getRow() - i][pos.getColumn()].getValue()
                            >= tile.getValue()) {
                        return false;
                    }
                }
            }
            if (pos.getRow() + i < getSize()) {
                if (tiles[pos.getRow() + i][pos.getColumn()] != null) {
                    if (tiles[pos.getRow() + i][pos.getColumn()].getValue()
                            <= tile.getValue()) {
                        return false;
                    }
                }
            }

        }

        return true;
    }

    /**
     * Inserts the given tile at the given position without verifying the
     * condition if it can be inserted or not.
     *
     * @param tile The tile to insert.
     * @param pos Position in which the tile will be inserted.
     */
    public void put(Tile tile, Position pos) {
        tiles[pos.getRow()][pos.getColumn()] = tile;
    }

    /**
     * Verifies if the array of tiles is already full or not.
     *
     * @return True if it's full, if not, false.
     */
public boolean isFull() {
        for (Tile[] lg : tiles) {
            for (Tile col : lg) {
                if (col == null) {
                    return false;
                }
            }
        }
        return true;
    }

}
