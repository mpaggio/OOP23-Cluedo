package it.unibo.cluedo.model.room.impl;

import java.util.List;
import java.util.LinkedList;

import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;

public class RoomImpl implements Room {
    private String name;
    private List<Square> squares;
    private List<Square> entrances;
    // private TrapDoor trapDoor;

    public RoomImpl(final String name) {
        this.name = name;
        this.squares = new LinkedList<>();
        this.entrances = new LinkedList<>();
    }

    @Override
    public void addSquare(Square square) {
        this.squares.add(square);
    }

    @Override
    public void addEntrance(Square entrance) {
        this.entrances.add(entrance);
    }

    @Override
    public boolean isEntrance(Square square) {
        if (this.entrances.contains(square)) {
            return true;
        }
        return false;
    }

    @Override
    public Square getCenterSquare() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCenterSquare'");
    }

    @Override
    public void setTrapDoor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTrapDoor'");
    }

    @Override
    public String toString(){
        return this.name;
    }
    
}
