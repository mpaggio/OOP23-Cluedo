package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Position;

/**
 * Implementation of the {@link MovementStrategy} interface.
 * Provides the basic movement and validation operation.
 */
public final class BoardMovement implements MovementStrategy {

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
        return newPosition.getX() >= 0 && newPosition.getX() < boardSize
               &&  newPosition.getY() >= 0 && newPosition.getY() < boardSize;
    }
}
