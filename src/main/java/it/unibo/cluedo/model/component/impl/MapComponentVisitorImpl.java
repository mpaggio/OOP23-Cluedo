package it.unibo.cluedo.model.component.impl;

import java.util.List;
import java.util.Optional;
import java.util.LinkedList;

import it.unibo.cluedo.model.component.api.MapComponentVisitor;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

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
        return new LinkedList<>(this.visitedRoom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Square> getVisitedSquare() {
        return new LinkedList<>(this.visitedSquare);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Square getSquareByPosition(final Position position) {
        return this.visitedSquare.stream()
            .filter(square -> square.getPosition().equals(position))
            .findAny()
            .get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSquareInRoom(final Square square) {
        return this.visitedRoom.stream()
            .anyMatch(room -> room.getSquares().contains(square));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Room> getRoomBySquare(final Square square) {
        return this.getVisitedRoom().stream()
            .filter(room -> room.getSquares().contains(square))
            .findAny();
    }
}
