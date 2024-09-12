package it.unibo.cluedo.model.board.api;

import java.util.List;
import java.util.Optional;

import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Represents the map of the game of Cluedo.
 */
public interface Board {
    /**
     * Get the list of squares of the map.
     * 
     * @return the list of squares of the map
     */
    List<Square> getSquares();

    /**
     * Gets the list of rooms of the map.
     * 
     * @return the list of rooms of the map
     */
    List<Room> getRooms();

    /**
     * Gets the square by the specified position.
     * 
     * @param position the position of the square to find
     * @return the square with the specified position
     */
    Square getSquareByPosition(Position position);

    /**
     * Check if the given square is part of a room.
     * 
     * @param square the square to be checked
     * @return true if the square is part of a room, false otherwise
     */
    boolean isSquareInRoom(Square square);

    /**
     * Gets the room having the specified square if exists.
     * 
     * @param square the square to check
     * @return the room with the square if present 
     */
    Optional<Room> getRoomBySquare(Square square);

    /**
     * Gets the room having the specified name if exists.
     * 
     * @param roomName the name of the room
     * @return the room with the specified name if exists
     */
    Optional<Room> getRoomByName(String roomName);

    /**
     * Returns a list of squares of the map ordered by position.
     * 
     * @return a list of of squares of the map ordered by position
     */
    List<Square> getOrderedVisitedSquares();

    /**
     * Resets the effected player for every square of the map.
     */
    void resetAllEffectedSquares();
}
