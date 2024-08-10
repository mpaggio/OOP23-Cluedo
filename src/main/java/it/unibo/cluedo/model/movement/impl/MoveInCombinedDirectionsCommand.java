package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.movement.api.MovementCommand;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Position;

/**
 * Command for moving a player in combined directions.
 */
public final class MoveInCombinedDirectionsCommand implements MovementCommand {

    private final Player player;
    private final int verticalSteps;
    private final int horizontalSteps;
    private final MovementStrategy.Direction verticalDirection;
    private final MovementStrategy.Direction horizontalDirection;
    private final MovementStrategy movementStrategy;

    /**
     * Constructs a new command for moving the player in combined directions.
     *
     * @param player the player to be moved
     * @param verticalSteps the number of steps to move vertically
     * @param horizontalSteps the number of steps to move horizontally
     * @param verticalDirection the vertical direction (UP or DOWN)
     * @param horizontalDirection the horizontal direction (LEFT or RIGHT)
     * @param movementStrategy the strategy used to calculate and validate the movement
     */
    public MoveInCombinedDirectionsCommand(final Player player, final int verticalSteps, final int horizontalSteps,
                                           final MovementStrategy.Direction verticalDirection,
                                           final MovementStrategy.Direction horizontalDirection,
                                           final MovementStrategy movementStrategy) {
        this.player = player;
        this.verticalSteps = verticalSteps;
        this.horizontalSteps = horizontalSteps;
        this.verticalDirection = verticalDirection;
        this.horizontalDirection = horizontalDirection;
        this.movementStrategy = movementStrategy;
    }

    @Override
    public void execute() {
        // Movimento verticale
        Position newPosition = movementStrategy.calculatePosition(
            player.getCurrentPosition(), verticalSteps, verticalDirection
        );

        if (movementStrategy.isValidMove(player, newPosition, 10)) {  // Supponendo una scacchiera 10x10
            player.setPosition(newPosition);
        } else {
            throw new IllegalArgumentException(
                "Invalid vertical move: The player cannot move outside the board or into an invalid area."
            );
        }

        // Movimento orizzontale
        newPosition = movementStrategy.calculatePosition(
            player.getCurrentPosition(), horizontalSteps, horizontalDirection
        );

        if (movementStrategy.isValidMove(player, newPosition, 10)) {
            player.setPosition(newPosition);
        } else {
            throw new IllegalArgumentException(
                "Invalid horizontal move: The player cannot move outside the board or into an invalid area."
            );
        }
    }
}
