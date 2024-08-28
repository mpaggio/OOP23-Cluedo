package it.unibo.cluedo.model.component.api;

import java.util.List;
import java.util.Optional;

import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Interface made for pattern visitor of the map component.
 */
public interface MapComponentVisitor {
    /**
     * Visit a square component.
     * 
     * @param square the square to visit
     */
    void visitSquare(Square square);

    /**
     * Visit a room component.
     * 
     * @param room the room to visit
     */
    void visitRoom(Room room);

    /**
     * Gets the list of visited rooms.
     * 
     * @return the list of visited rooms
     */
    List<Room> getVisitedRoom();

    /**
     * Gets the list of visited squares.
     * 
     * @return the list of visited squares
     */
    List<Square> getVisitedSquare();

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
     * Returns the string representing the created map.
     * 
     * @return the string representing the created map
     */
    String printMap();
}
