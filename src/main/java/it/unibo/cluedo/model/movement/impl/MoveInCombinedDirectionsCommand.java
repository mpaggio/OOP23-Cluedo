
package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.movement.api.MovementCommand;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import java.util.List;
import java.util.Collections;
/**
 * Command for moving a player in combined directions.
 */
public final class MoveInCombinedDirectionsCommand implements MovementCommand {

    private final Player player;
    private final List<MovementStrategy.Direction> directions;
    private final MovementStrategy movementStrategy;
    private static final int STEPS = 1;

    /**
     * Constructor a new command for moving the player in multiple directions.
     * @param player player to move
     * @param directions list of directions where to move
     * @param movementStrategy the strategy used to calculate and validate the movement
     */
    public MoveInCombinedDirectionsCommand(final Player player, final List<MovementStrategy.Direction> directions,
                                        final MovementStrategy movementStrategy) {
        this.player = player;
        this.directions = Collections.unmodifiableList(directions);
        this.movementStrategy = movementStrategy;
    }

    @Override
    public void execute() {
        for (final var direction : directions) {
            final MovementCommand singleMovementCommand = new MoveInSingleDirection(
                (MutablePlayer) this.player, STEPS, direction, movementStrategy);
            singleMovementCommand.execute();
        }
    }
 
}
