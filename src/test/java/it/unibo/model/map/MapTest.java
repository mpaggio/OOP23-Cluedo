package it.unibo.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cluedo.model.component.api.MapComponent;
import it.unibo.cluedo.model.map.api.Map;
import it.unibo.cluedo.model.map.impl.MapImpl;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.model.square.impl.BonusEffectImpl;
import it.unibo.cluedo.model.square.impl.MalusEffectImpl;
import it.unibo.cluedo.utilities.Position;

final class MapTest {
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
    private Map map;

    @BeforeEach
    void setUp() {
        this.map = new MapImpl();
    }

    @Test
    void testMapInitialization() {
        for (final MapComponent component : this.map.getMap()) {
            assertTrue(component.hasBeenVisited());
        }
        assertNotNull(this.map.getMap(), "Shouldn't be null");
        assertNotNull(this.map.getVisitor(), "Visitor shouldn't be null");
        assertEquals(map.getVisitor().getVisitedRoom().size(), NUM_OF_ROOMS);
        for (final Position pos : Position.getDefaultPositions()) {
            assertFalse(map.getVisitor().isSquareInRoom(map.getVisitor().getSquareByPosition(pos)));
        }
    }

    @Test
    void testMapRooms() {
        for (final String roomName : ROOM_NAMES) {
            assertTrue(
                map.getVisitor().getVisitedRoom().stream()
                    .anyMatch(room -> room.getName().equals(roomName)),
                    roomName + " should be present"
            );
        }
        for (final Room room : map.getVisitor().getVisitedRoom()) {
            final List<Square> roomNormalSquares = room.getSquares().stream()
                .filter(square -> square.getEffect().getType().equals(Effect.EffectType.NONE))
                .toList();
            assertFalse(room.getEntrances().isEmpty());
            assertFalse(room.getSquares().isEmpty());
            assertEquals(roomNormalSquares.size(), room.getSquares().size());
            assertTrue(roomNormalSquares.containsAll(room.getSquares()));
        }
        assertEquals(
            this.map.getVisitor().getVisitedRoom().stream()
                .filter(Room::hasTrapDoor)
                .count(),
            NUM_ROOMS_WITH_TRAPDOOR
        );
        for (final String roomName : ROOMS_WITH_TRAPDOOR) {
            assertTrue(
                map.getVisitor().getVisitedRoom().stream()
                    .anyMatch(room -> room.getName().equals(roomName) && room.hasTrapDoor()),
                    roomName + " should be present"
            );
        }
    }

    @Test
    void testMapSquares() {
        assertEquals(
            NUM_SQUARES_WITH_EFFECT_PER_TYPE, 
            map.getVisitor().getVisitedSquare().stream()
                .filter(square -> square.getEffect() instanceof BonusEffectImpl)
                .count()
        );
        assertEquals(
            NUM_SQUARES_WITH_EFFECT_PER_TYPE, 
            map.getVisitor().getVisitedSquare().stream()
                .filter(square -> square.getEffect() instanceof MalusEffectImpl)
                .count()
        );
    }

    @Test
    void testMapEffectiveSquare() {
        //System.out.println(map.getVisitor().printMap());
        final List<Square> effectiveSquares = map.getVisitor().getVisitedSquare().stream()
            .filter(square -> !square.getEffect().getType().equals(Effect.EffectType.NONE))
            .toList();
        final List<Square> bonusSquares = map.getVisitor().getVisitedSquare().stream()
            .filter(square -> square.getEffect().getType().equals(Effect.EffectType.BONUS))
            .toList();
        final List<Square> malusSquares = map.getVisitor().getVisitedSquare().stream()
            .filter(square -> square.getEffect().getType().equals(Effect.EffectType.MALUS))
            .toList();
        final Set<Position> positions = new HashSet<>();
        for (final Square square : bonusSquares) {
            assertFalse(map.getVisitor().isSquareInRoom(square));
        }
        for (final Square square : malusSquares) {
            assertFalse(map.getVisitor().isSquareInRoom(square));
        }
        for (final Square square : effectiveSquares) {
            assertTrue(positions.add(square.getPosition()));
        }
    }

    @Test
    void testMapComponent() {
        final Player player1 = new MutablePlayerImpl("Marco", "Red");
        final Player player2 = new MutablePlayerImpl("Luca", "Green");
        final Player player3 = new MutablePlayerImpl("Giovanni", "Black");
        for (final MapComponent component : this.map.getMap()) {
            assertTrue(component.getPlayersIn().isEmpty());
        }
        for (final Room room : map.getVisitor().getVisitedRoom()) {
            room.addPlayerInRoom(player1);
            room.addPlayerInRoom(player2);
            room.addPlayerInRoom(player3);
        }
        for (final Square square : map.getVisitor().getVisitedSquare()) {
            square.landOn(player1);
        }
        for (final MapComponent component : this.map.getMap()) {
            assertFalse(component.getPlayersIn().isEmpty());
            if (component instanceof Square) {
                assertEquals(component.getPlayersIn().size(), 1);
            } else {
                assertNotEquals(component.getPlayersIn().size(), 1);
            }
        }
    }
}
