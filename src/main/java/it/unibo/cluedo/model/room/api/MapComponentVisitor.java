package it.unibo.cluedo.model.room.api;

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
}
