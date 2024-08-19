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
    private Optional<Square> lastVisitedSquare;
    private Optional<Room> lastVisitedRoom;

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
            this.lastVisitedSquare = Optional.of(square);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitRoom(final Room room) {
        if (!this.visitedRoom.contains(room)) {
            this.visitedRoom.add(room);
            this.lastVisitedRoom = Optional.of(new RoomImpl(room.getName()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Room> getLastVisitedRoom() {
        return this.lastVisitedRoom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Square> getLastVisitedSquare() {
        return this.lastVisitedSquare;
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
