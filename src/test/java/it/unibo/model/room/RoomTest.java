package it.unibo.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.room.impl.RoomImpl;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.square.impl.SquareFactory;
import it.unibo.cluedo.model.trapdoor.api.TrapDoor;
import it.unibo.cluedo.model.trapdoor.impl.TrapDoorImpl;
import it.unibo.cluedo.utilities.Position;

final class RoomTest {
    private static final String ROOM_NAME_1 = "Kitchen";
    private static final String ROOM_NAME_2 = "Billiard room";
    private static final int NUM_OF_SQUARES = 3;
    private static final int NUM_OF_ENTRANCES = 2;
    private Room room1;
    private TrapDoor trapDoor;
    private Square square1;
    private Square square2;
    private Square square3;
    private Square entrance1;
    private Square entrance2;

    @BeforeEach
    void setUp() {
        final Room room2 = new RoomImpl(ROOM_NAME_2);
        this.room1 = new RoomImpl(ROOM_NAME_1);
        this.trapDoor = new TrapDoorImpl(room2, new Position(0, 0));
        this.square1 = SquareFactory.createNormalSquare(new Position(1, 0));
        this.square2 = SquareFactory.createNormalSquare(new Position(0, 1));
        this.square3 = SquareFactory.createNormalSquare(new Position(1, 1));
        this.entrance1 = square1;
        this.entrance2 = square2;
    }

    @Test
    void testAddSquare() {
        this.room1.addSquare(square1);
        assertTrue(this.room1.getSquares().contains(square1));
        this.room1.addSquare(square2);
        assertTrue(this.room1.getSquares().contains(square1));
        assertTrue(this.room1.getSquares().contains(square2));
        this.room1.addSquare(square3);
        assertTrue(this.room1.getSquares().contains(square1));
        assertTrue(this.room1.getSquares().contains(square2));
        assertTrue(this.room1.getSquares().contains(square3));
        assertEquals(this.room1.getSquares().size(), NUM_OF_SQUARES);
    }

    @Test
    void testAddEntrance() {
        this.room1.addEntrance(entrance1);
        assertTrue(this.room1.getEntrances().contains(entrance1));
        this.room1.addEntrance(entrance2);
        assertTrue(this.room1.getEntrances().contains(entrance1));
        assertTrue(this.room1.getEntrances().contains(entrance2));
        assertEquals(this.room1.getEntrances().size(), NUM_OF_ENTRANCES);
    }

    @Test
    void testSetTrapDoor() {
        this.room1.setTrapDoor(Optional.of(this.trapDoor));
        assertTrue(this.room1.hasTrapDoor());
    }

    @Test
    void testToStringAndGetName() {
        assertEquals(this.room1.toString(), this.room1.getName());
        assertEquals(this.room1.getName(), ROOM_NAME_1);
        assertEquals(this.room1.toString(), ROOM_NAME_1);
    }

    @Test
    void testIsEntrance() {
        this.room1.addEntrance(entrance1);
        this.room1.addEntrance(entrance2);
        assertTrue(this.room1.isEntrance(this.square1));
        assertTrue(this.room1.isEntrance(this.square2));
        assertFalse(this.room1.isEntrance(this.square3));
        assertTrue(this.room1.isEntrance(this.entrance1));
        assertTrue(this.room1.isEntrance(this.entrance2));
    }
}
