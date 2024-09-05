package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.utilities.Position;
/**
 * Implementation of the {@link MovementStrategy} interface.
 * Provides the basic movement and validation operation.
 */
public final class BoardMovement implements MovementStrategy {
    private final Board map;
    private final int width;
    private final int heigth;

    /**
     * Constructor for BoardMovement.
     * @param map the map of Cluedo game
     */
    public BoardMovement(final Board map) {
        this.map = map;
        this.width = BoardImpl.getMapWidth();
        this.heigth = BoardImpl.getMapHeight();
    }

    @Override
    public Position calculatePosition(final Position currentPosition, final int steps, final Direction direction) {
        switch (direction) {
            case UP:
                return new Position(currentPosition.getX() - steps, currentPosition.getY());
            case DOWN:
                return new Position(currentPosition.getX() + steps, currentPosition.getY());
            case RIGHT:
                return new Position(currentPosition.getX(), currentPosition.getY() + steps);
            case LEFT:
                return new Position(currentPosition.getX(), currentPosition.getY() - steps);
            default:
                throw new IllegalStateException("Invalid direction: " + direction);
        }
    }

    @Override
    public boolean isValidMove(final Player player, final Position newPosition) {
        return newPosition.getX() >= 0 && newPosition.getX() < this.width
        && newPosition.getY() >= 0 && newPosition.getY() < this.heigth
        && !map.getSquareByPosition(newPosition).isAlreadyOccupied();
    }

    @Override
    public boolean isTrapDoorUsable(final Player player) {
        return map.getRooms().stream()
            .filter(r -> r.isPlayerInRoom(player))
            .filter(Room::hasTrapDoor)
            .map(room -> room.getTrapDoor().get().getConnectedRoom())
            .findAny()
            .isPresent();
    }

    @Override
    public boolean hasPlayerEnteredInRoom(final Player player, final Position newPosition) {
        return map.getRooms().stream()
            .anyMatch(room -> room.isEntrance(this.map.getSquareByPosition(newPosition)));
    }
}
