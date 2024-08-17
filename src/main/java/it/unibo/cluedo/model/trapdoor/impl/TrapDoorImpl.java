package it.unibo.cluedo.model.trapdoor.impl;

import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.trapdoor.api.TrapDoor;
import it.unibo.cluedo.utilities.Position;

public class TrapDoorImpl implements TrapDoor {
    private final Room connectedRoom;
    private final Position position;

    public TrapDoorImpl(final Room connectedRoom, final Position position) {
        this.connectedRoom = connectedRoom;
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Room getConnectedRoom() {
        return this.connectedRoom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return this.position;
    }
    
}
