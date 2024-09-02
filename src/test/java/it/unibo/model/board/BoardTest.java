package it.unibo.model.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.square.impl.BonusEffectImpl;
import it.unibo.cluedo.model.square.impl.MalusEffectImpl;
import it.unibo.cluedo.utilities.Position;

final class BoardTest {
    private static final int NUM_OF_ROOMS = 10;
    private static final int NUM_ROOMS_WITH_TRAPDOOR = 4;
    private static final int NUM_SQUARES_WITH_EFFECT_PER_TYPE = 3;
    private static final List<String> ROOM_NAMES = List.of(
        "Kitchen", "Ballroom", "Conservatory", "Billiard room",
        "Library", "Study", "Hall", "Lounge", "Dining room",
        "Cluedo"
    );
    private static final List<String> ROOMS_WITH_TRAPDOOR = List.of(
        "Kitchen", "Conservatory", "Study", "Lounge"
    );
    private Board map;

    @BeforeEach
    void setUp() {
        this.map = new BoardImpl();
    }

    @Test
    void testMapInitialization() {
        assertNotNull(this.map.getSquares(), "Shouldn't be null");
        assertNotNull(this.map.getRooms(), "Shouldn't be null");
        assertEquals(map.getRooms().size(), NUM_OF_ROOMS);
        for (final Position pos : Position.getDefaultPositions()) {
            assertFalse(map.isSquareInRoom(map.getSquareByPosition(pos)));
        }
    }

    @Test
    void testMapRooms() {
        for (final String roomName : ROOM_NAMES) {
            assertTrue(
                map.getRooms().stream()
                    .anyMatch(room -> room.getName().equals(roomName)),
                    roomName + " should be present"
            );
        }
        for (final Room room : map.getRooms()) {
            final List<Square> roomNormalSquares = room.getSquares().stream()
                .filter(square -> square.getEffect().getType().equals(Effect.EffectType.NONE))
                .toList();
            assertFalse(room.getEntrances().isEmpty());
            assertFalse(room.getSquares().isEmpty());
            assertEquals(roomNormalSquares.size(), room.getSquares().size());
            assertTrue(roomNormalSquares.containsAll(room.getSquares()));
            assertTrue(room.getSquares().containsAll(room.getEntrances()));
        }
        assertEquals(
            this.map.getRooms().stream()
                .filter(Room::hasTrapDoor)
                .count(),
            NUM_ROOMS_WITH_TRAPDOOR
        );
        for (final String roomName : ROOMS_WITH_TRAPDOOR) {
            assertTrue(
                map.getRooms().stream()
                    .anyMatch(room -> room.getName().equals(roomName) && room.hasTrapDoor()),
                    roomName + " should be present"
            );
        }
    }

    @Test
    void testMapSquares() {
        assertEquals(
            NUM_SQUARES_WITH_EFFECT_PER_TYPE, 
            map.getSquares().stream()
                .filter(square -> square.getEffect() instanceof BonusEffectImpl)
                .count()
        );
        assertEquals(
            NUM_SQUARES_WITH_EFFECT_PER_TYPE, 
            map.getSquares().stream()
                .filter(square -> square.getEffect() instanceof MalusEffectImpl)
                .count()
        );
    }

    @Test
    void testMapEffectiveSquare() {
        System.out.println(map.printMap());
        System.out.println("\n");
        /*for(final Square square : map.getOrderedVisitedSquares()) {
            System.out.println(square.getPosition().toString());
        }*/
        final List<Square> effectiveSquares = map.getSquares().stream()
            .filter(square -> !square.getEffect().getType().equals(Effect.EffectType.NONE))
            .toList();
        final List<Square> bonusSquares = map.getSquares().stream()
            .filter(square -> square.getEffect().getType().equals(Effect.EffectType.BONUS))
            .toList();
        final List<Square> malusSquares = map.getSquares().stream()
            .filter(square -> square.getEffect().getType().equals(Effect.EffectType.MALUS))
            .toList();
        final Set<Position> positions = new HashSet<>();
        for (final Square square : bonusSquares) {
            assertFalse(map.isSquareInRoom(square));
        }
        for (final Square square : malusSquares) {
            assertFalse(map.isSquareInRoom(square));
        }
        for (final Square square : effectiveSquares) {
            assertTrue(positions.add(square.getPosition()));
        }
    }
}
