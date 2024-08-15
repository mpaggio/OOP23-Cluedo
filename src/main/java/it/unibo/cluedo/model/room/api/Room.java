package it.unibo.cluedo.model.room.api;

import it.unibo.cluedo.model.square.api.Square;

/**
 * Represents a room in the Cluedo game.
 * A room contains squares and has at least one entrance.
 * A room can contain a trap door.
 */
public interface Room {
    /**
     * Adds a square to the room.
     * 
     * @param square the square to be added to the room
     */
    void addSquare(Square square);

    /**
     * Adds an entrance to the room.
     * 
     * @param entrance the square representing the entrance to the room
     */
    void addEntrance(Square entrance);

    /**
     * Checks if a given square is an entrance to the room.
     * 
     * @param square the square to be checked
     * @return true if the square is an entrance, false otherwise
     */
    boolean isEntrance(Square square);

    /**
     * Sets a trap door in the room.
     */
    void setTrapDoor();
}
