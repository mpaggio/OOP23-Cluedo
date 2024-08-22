package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.impl.NoEffectImpl;
import it.unibo.cluedo.model.square.impl.SquareImpl;
import it.unibo.cluedo.utilities.Position;
import java.util.List;
import java.util.ArrayList;
/**
 * Implementation of the {@link MovementStrategy} interface.
 * Provides the basic movement and validation operation.
 */
public final class BoardMovement implements MovementStrategy {

    private final List<Room> rooms = new ArrayList<>();

    @Override
    public Position calculatePosition(final Position currentPosition, final int steps, final Direction direction) {
        switch (direction) {
            case UP:
                return new Position(currentPosition.getX(), currentPosition.getY() - steps);
            case DOWN:
                return new Position(currentPosition.getX(), currentPosition.getY() + steps);
            case RIGHT:
                return new Position(currentPosition.getX() + steps, currentPosition.getY());
            case LEFT:
                return new Position(currentPosition.getX() - steps, currentPosition.getY());
            default:
                throw new IllegalStateException("Invalid direction: " + direction);
        }
    }

    @Override
    public boolean isValidMove(final Player player, final Position newPosition, final int boardSize) {
        if (newPosition.getX() < 0 || newPosition.getX() >= boardSize 
        || newPosition.getY() < 0 || newPosition.getY() >= boardSize) {
            return false;
        }
        final boolean isEntrance = rooms.stream()
            .flatMap(rooom -> rooom.getEntrances().stream()) //unico flusso continuo di "square"
            .anyMatch(entrance -> entrance.getPosition().equals(newPosition));
        if (isEntrance) {
            return true;
        }
        return rooms.stream()
            .filter(room -> room.getSquares().contains(new SquareImpl(newPosition, new NoEffectImpl())))
            .filter(Room::hasTrapDoor) //seleziono solo le stanze che hanno una botola e che contengono newPosition
            .map(room -> room.getTrapDoor().get().getConnectedRoom()) //trasformo lo stream di room in uno streem di di 
            //stanze collegate tramite botola
            .flatMap(connectedRoom -> connectedRoom.getSquares().stream())
            .anyMatch(square -> square.getPosition().equals(newPosition));
        }
}
