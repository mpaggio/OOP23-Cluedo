
package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.map.api.Map;
import it.unibo.cluedo.model.movement.api.MovementCommand;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
/**
 * Command for moving a player in combined directions.
 */
public final class MoveInCombinedDirectionsCommand implements MovementCommand {

    private final Player player;
    private final MovementStrategy.Direction direction;
    private final MovementStrategy movementStrategy;
    private static final int STEPS = 1;
    private final Map map;
    /**
     * Constructor a new command for moving the player in multiple directions.
     * @param player player to move
     * @param direction the direction where to move
     * @param movementStrategy the strategy used to calculate and validate the movement
     * @param map the map of Cluedo game
     */
    public MoveInCombinedDirectionsCommand(final Player player, final MovementStrategy.Direction direction,
                                        final MovementStrategy movementStrategy, final Map map) {
        this.player = player;
        this.direction = direction;
        this.movementStrategy = movementStrategy;
        this.map = map;
    }

    @Override
    public void execute() {
        final MovementCommand singleMovementCommand = new MoveInSingleDirection(
            (MutablePlayer) this.player, STEPS, direction, movementStrategy, map);
            singleMovementCommand.execute();
    }

}
