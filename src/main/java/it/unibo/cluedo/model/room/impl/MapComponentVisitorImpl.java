package it.unibo.cluedo.model.room.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.cluedo.model.room.api.MapComponentVisitor;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;

/**
 * Implementation of the map component visitor (visitor pattern).
 */
public class MapComponentVisitorImpl implements MapComponentVisitor {
    private final List<Square> visitedSquare;
    private final List<Room> visitedRoom;

    /**
     * Constructor for the map component visitor.
     */
    public MapComponentVisitorImpl() {
        this.visitedSquare = new LinkedList<>();
        this.visitedRoom = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitSquare(final Square square) {
        if (!this.visitedSquare.contains(square)) {
            this.visitedSquare.add(square);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitRoom(final Room room) {
        if (!this.visitedRoom.contains(room)) {
            this.visitedRoom.add(room);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Room> getVisitedRoom() {
        return List.copyOf(this.visitedRoom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Square> getVisitedSquare() {
        return List.copyOf(this.visitedSquare);
    }
}
