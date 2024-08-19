package it.unibo.cluedo.model.trapdoor.impl;

import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.room.impl.RoomImpl;
import it.unibo.cluedo.model.trapdoor.api.TrapDoor;
import it.unibo.cluedo.utilities.Position;

/**
 * Implementation representing the trap door in the Cluedo game.
 */
public class TrapDoorImpl implements TrapDoor {
    private final Room connectedRoom;
    private final Position position;

    /**
     * Constructor for the trap door.
     * 
     * @param connectedRoom the room connected to the trap door
     * @param position the position of the trap door in the room
     */
    public TrapDoorImpl(final Room connectedRoom, final Position position) {
        this.connectedRoom = new RoomImpl(connectedRoom.getName());
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Room getConnectedRoom() {
        return new RoomImpl(this.connectedRoom.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return this.position;
    }
}
