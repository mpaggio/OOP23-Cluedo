package it.unibo.cluedo.model.component.impl;

import java.util.List;
import java.util.Optional;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

import it.unibo.cluedo.model.component.api.MapComponentVisitor;
import it.unibo.cluedo.model.map.impl.MapImpl;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Effect;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Implementation of the map component visitor (visitor pattern).
 */
public class MapComponentVisitorImpl implements MapComponentVisitor {
    private final List<Square> visitedSquare;
    private final List<Room> visitedRoom;

    /**
     * Constructor for the map component visitor.
     */
    public MapComponentVisitorImpl() {
        this.visitedSquare = new LinkedList<>();
        this.visitedRoom = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitSquare(final Square square) {
        if (!this.visitedSquare.contains(square)) {
            this.visitedSquare.add(square);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitRoom(final Room room) {
        if (!this.visitedRoom.contains(room)) {
            this.visitedRoom.add(room);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Room> getVisitedRoom() {
        return new LinkedList<>(this.visitedRoom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Square> getVisitedSquare() {
        return new LinkedList<>(this.visitedSquare);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Square getSquareByPosition(final Position position) {
        return this.visitedSquare.stream()
            .filter(square -> square.getPosition().equals(position))
            .findAny()
            .get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSquareInRoom(final Square square) {
        return this.visitedRoom.stream()
            .anyMatch(room -> room.getSquares().contains(square));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Room> getRoomBySquare(final Square square) {
        return this.getVisitedRoom().stream()
            .filter(room -> room.getSquares().contains(square))
            .findAny();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String printMap() {
        final Map<Position, Character> positionToSymbolMap = new HashMap<>();
        for (final Room room : visitedRoom) {
            for (final Square square : room.getSquares()) {
                positionToSymbolMap.put(square.getPosition(), '*');
            }
            for (final Square square : room.getEntrances()) {
                positionToSymbolMap.put(square.getPosition(), '=');
            }
            if (room.hasTrapDoor()) {
                positionToSymbolMap.put(room.getTrapDoor().get().getPosition(), '<');
            }
        }
        for (final Square square : visitedSquare) {
            final Position position = square.getPosition();
            if (square.getEffect().getType().equals(Effect.EffectType.BONUS)) {
                positionToSymbolMap.put(position, '$');
            } else if (square.getEffect().getType().equals(Effect.EffectType.MALUS)) {
                positionToSymbolMap.put(position, '%');
            } else {
                positionToSymbolMap.put(position, '_');
            }
        }
        final List<Position> sortedPositions = new LinkedList<>(positionToSymbolMap.keySet());
        Collections.sort(
            sortedPositions,
            Comparator.comparingInt(Position::getX).thenComparingInt(Position::getY)
        );
        final StringBuilder mapBuilder = new StringBuilder();
        for (int i = 0; i < MapImpl.getMapHeight(); i++) {
            for (int j = 0; j < MapImpl.getMapWidth(); j++) {
                final Position position = new Position(i, j);
                final Character symbol = positionToSymbolMap.get(position);
                if (symbol != null) {
                    mapBuilder.append(symbol).append(' ');
                } else {
                    mapBuilder.append(". ");
                }
            }
            mapBuilder.append('\n');
        }
        return mapBuilder.toString();
    }
}
