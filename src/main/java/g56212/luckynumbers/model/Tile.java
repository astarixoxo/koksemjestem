package g56212.luckynumbers.model;

/**
 *
 * @author piotr
 */
public class Tile {

    private int value;

    /**
     *  Initializes this object(tile) with int as it's value.
     * @param value the value of the tile.
     */
    public Tile(int value) {
        this.value = value;
    }

    /**
     * Getter of the value that returns this value as an integer
     * @return value of this.value as an integer
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tile other = (Tile) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }

}
