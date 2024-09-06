package it.unibo.model.trapdoor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.room.impl.RoomImpl;
import it.unibo.cluedo.model.trapdoor.api.TrapDoor;
import it.unibo.cluedo.model.trapdoor.impl.TrapDoorImpl;
import it.unibo.cluedo.utilities.Position;

/**
 * Test class for SquareImpl, SquareFactory and Effect implementations.
 */
final class TrapDoorTest {
    private Room room;
    private Position position;
    private TrapDoor trapDoor;

    /**
     * Initialization before every test.
     */
    @BeforeEach
    void setUp() {
        this.room = new RoomImpl("Kitchen");
        this.position = new Position(0, 0);
        this.trapDoor = new TrapDoorImpl(this.room, this.position);
    }

    @Test
    void testTrapDoor() {
        assertNotEquals(this.room, this.trapDoor.getConnectedRoom());
        assertEquals(this.room.getName(), this.trapDoor.getConnectedRoom());
        assertEquals(this.position, this.trapDoor.getPosition());
    }
}
