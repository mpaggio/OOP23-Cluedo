package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.component.api.MapComponentVisitor;
import it.unibo.cluedo.model.map.api.Map;
import it.unibo.cluedo.model.map.impl.MapImpl;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.utilities.Position;
/**
 * Implementation of the {@link MovementStrategy} interface.
 * Provides the basic movement and validation operation.
 */
public final class BoardMovement implements MovementStrategy {
    private final MapComponentVisitor visitor;
    private final int width;
    private final int heigth;

    /**
     * Constructor for BoardMovement.
     * @param map the map of Cluedo game
     */
    public BoardMovement(final Map map) {
        this.visitor = map.getVisitor();
        this.width = MapImpl.getMapWidth();
        this.heigth = MapImpl.getMapHeight();
    }

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
    public boolean isValidMove(final Player player, final Position newPosition) {
        return newPosition.getX() >= 0 && newPosition.getX() < this.width
        && newPosition.getY() >= 0 && newPosition.getY() < this.heigth
        && visitor.getSquareByPosition(newPosition).isAlreadyOccupied();
    }

    @Override
    public boolean isTrapDoorUsable(final Player player) {
        return visitor.getVisitedRoom().stream()
            .filter(r -> r.isPlayerInRoom(player))
            .filter(Room::hasTrapDoor)
            .map(room -> room.getTrapDoor().get().getConnectedRoom())
            .findAny()
            .isPresent();
    }

    @Override
    public boolean hasPlayerEnteredInRoom(final Player player, final Position newPosition) {
        return visitor.getVisitedRoom().stream()
            .anyMatch(room -> room.isEntrance(this.visitor.getSquareByPosition(newPosition)));
    }
}
