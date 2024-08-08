package it.unibo.cluedo.utilities;

import java.util.List;

/**
 * This record model a 2-D position.
 * 
 * @param x - x axis coordinate
 * @param y - y axis coordinate
 */
public record Position(int x, int y) {

    /**
     * Default position 1 (0,0).
     */
    public static final Position DEFAULT_POSITION_1 = new Position(0, 0);

    /**
     * Default position 2 (5,5).
     */
    public static final Position DEFAULT_POSITION_2 = new Position(5, 5);

    /**
     * Default position 3 (10,10).
     */
    public static final Position DEFAULT_POSITION_3 = new Position(10, 10);

    /**
     * Method to get the x coordinate.
     * 
     * @return - x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Method to get te y coordinate.
     * 
     * @return - y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Method to get the default position of the players.
     * 
     * @return a list of Position
     */
    public static List<Position> getDefaultPositions() {
        return List.of(
                DEFAULT_POSITION_1,
                DEFAULT_POSITION_2,
                DEFAULT_POSITION_3);
    }

}
