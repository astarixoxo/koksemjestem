package g56212.luckynumbers.model;

/**
 *
 * @author piotr
 */
public class Position {

    private int row;
    private int column;
/**
 * Constructor of a single position, needs the row and the column to be constructed and located in the 2d array.
 * @param row row of the 2d array.
 * @param column  column of the 2d array.
 */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
/**
 * Getter of this.row 
 * @return value of this.row as an integer.
 */
    public int getRow() {
        return row;
    }
/**
 * Getter of this.column
 * @return value of this.column as an integer.
 */
    public int getColumn() {
        return column;
    }
}
