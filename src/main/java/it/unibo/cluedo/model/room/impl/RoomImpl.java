package it.unibo.cluedo.model.room.impl;

import java.util.List;
import java.util.LinkedList;

import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;

/**
 * Implementation of the room of the Cluedo game.
 */
public class RoomImpl implements Room {
    private final String name;
    private final List<Square> squares;
    private final List<Square> entrances;
    // private TrapDoor trapDoor;

    /**
     * Constructor for the room.
     * 
     * @param name the name of the room
     */
    public RoomImpl(final String name) {
        this.name = name;
        this.squares = new LinkedList<>();
        this.entrances = new LinkedList<>();
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCenterSquare'");
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
