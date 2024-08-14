package it.unibo.cluedo.model.room.impl;

import java.util.List;
import java.util.LinkedList;

import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Implementation of the room of the Cluedo game.
 */
public class RoomImpl implements Room {
    private final String name;
    private final List<Square> squares;
    private final List<Square> entrances;
    // private final Position startPosition;
    // private final Position finalPosition;
    // private TrapDoor trapDoor;

    /**
     * Constructor for the room.
     * 
     * @param name the name of the room
     */
    public RoomImpl(final String name, final Position startPosition, final Position finalPosition) {
        this.name = name;
        this.squares = new LinkedList<>();
        this.entrances = new LinkedList<>();
        // this.startPosition = startPosition;
        // this.finalPosition = finalPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSquare(final Square square) {
        this.squares.add(square);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntrance(final Square entrance) {
        this.entrances.add(entrance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEntrance(final Square square) {
        return this.entrances.contains(square);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Square getCenterSquare() {
        return this.squares.get(this.squares.size() / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTrapDoor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTrapDoor'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name;
    }
}
