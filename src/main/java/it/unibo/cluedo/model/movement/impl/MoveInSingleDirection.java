package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.movement.api.MovementCommand;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Position;

/**
 * Command for moving a player in a single direction.
 */
public final class MoveInSingleDirection implements MovementCommand {

    private final Player player;
    private final int steps;
    private final MovementStrategy.Direction direction;
    private final MovementStrategy movementStrategy;

    /**
     * Construct a new command for moving the player in a single direction.
     * @param player the player to be moved
     * @param steps the number of steps to move on
     * @param direction the direction where to move (UP, DOWN, LEFT, RIGHT)
     * @param movementStrategy the strategy used to calculate and validate the movement
     */
    public MoveInSingleDirection(final MutablePlayer player, final int steps, final MovementStrategy.Direction direction,
                                 final MovementStrategy movementStrategy) {
        this.player = player.clone();
        this.steps = steps;
        this.direction = direction;
        this.movementStrategy = movementStrategy;
    }

    @Override
    public void execute() {
        final Position newPosition = movementStrategy.calculatePosition(player.getCurrentPosition(), steps, direction);

        if (movementStrategy.isValidMove(player, newPosition, 10)) {
            ((MutablePlayer) player).setPosition(newPosition);
        } else {
            throw new IllegalArgumentException("Invalid move: the player cannot move outside the board or into an invalid area");
        }
    }

}
