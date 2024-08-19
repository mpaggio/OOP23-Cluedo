package it.unibo.cluedo.model.room.api;

import java.util.List;
import java.util.Optional;

import it.unibo.cluedo.model.square.api.Square;

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
     * Gets last visited room.
     * 
     * @return last visited room
     */
    Optional<Room> getLastVisitedRoom();

    /**
     * Gets last visited square.
     * 
     * @return last visited square
     */
    Optional<Square> getLastVisitedSquare();

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
}
