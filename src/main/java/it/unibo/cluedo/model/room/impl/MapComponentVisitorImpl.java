package it.unibo.cluedo.model.room.impl;

import java.util.List;

import it.unibo.cluedo.model.room.api.MapComponentVisitor;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;

public class MapComponentVisitorImpl implements MapComponentVisitor {
    private List<Square> visitedSquare;
    private List<Room> visitedRoom;

    @Override
    public void visitSquare(final Square square) {
        if (!this.visitedSquare.contains(square)) {
            this.visitedSquare.addLast(square);
        }
    }

    @Override
    public void visitRoom(final Room room) {
        if (!this.visitedRoom.contains(room)) {
            this.visitedRoom.addLast(room);
        }
    }

    @Override
    public Room getLastVisitedRoom() {
        return this.visitedRoom.getLast();
    }

    @Override
    public Square getLastVisitedSquare() {
        return this.visitedSquare.getLast();
    }

    @Override
    public List<Room> getVisitedRoom() {
        return this.visitedRoom;
    }

    @Override
    public List<Square> getVisitedSquare() {
        return this.visitedSquare;
    }
    
}
