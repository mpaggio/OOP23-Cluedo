
package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.movement.api.MovementCommand;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Command for moving a player in combined directions.
 */
public final class MoveInCombinedDirectionsCommand implements MovementCommand {

    private final Player player;
    private final List<Movement> movements;
    private final MovementStrategy movementStrategy;

    /**
     * Represents a single movement step.
     */
    public static class Movement {
        private final int steps;
        private final MovementStrategy.Direction direction;

        /**
         * Construct a new movement.
         * @param steps the number of steps to move on
         * @param direction the direction where to move (UP, DOWN, LEFT, RIGHT)
         */
        public Movement(final int steps, final MovementStrategy.Direction direction) {
            this.steps = steps;
            this.direction = direction;
        }

        /**
         * {@inheritDoc}
         * @return the number of steps to move on
         */
        public int getSteps() {
            return steps;
        }

        /**
         * {@inheritDoc}
         * @return the direction where to move
         */
        public MovementStrategy.Direction getDirection() {
            return direction;
        }

    }

    /**
     * Constructor a new command for moving the player in multiple directions.
     * @param player player to move
     * @param movements the list of movements to perform
     * @param movementStrategy the strategy used to calculate and validate the movement
     */
    public MoveInCombinedDirectionsCommand(final MutablePlayer player, final List<Movement> movements,
                                        final MovementStrategy movementStrategy) {
        this.player = player.clone();
        this.movements = Collections.unmodifiableList(new ArrayList<>(movements));
        this.movementStrategy = movementStrategy;
    }

    @Override
    public void execute() {
        for (final var movement : movements) {
            final MovementCommand singleMovementCommand = new MoveInSingleDirection(
                (MutablePlayer) player, movement.getSteps(), movement.getDirection(), movementStrategy);
            singleMovementCommand.execute();
        }
    }
    
}
