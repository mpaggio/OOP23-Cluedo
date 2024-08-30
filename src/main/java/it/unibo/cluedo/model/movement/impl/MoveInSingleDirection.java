package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.component.api.MapComponentVisitor;
import it.unibo.cluedo.model.map.api.Map;
import it.unibo.cluedo.model.movement.api.MovementCommand;
import it.unibo.cluedo.model.movement.api.MovementStrategy;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.Position;

/**
 * Command for moving a player in a single direction.
 */
public final class MoveInSingleDirection implements MovementCommand {

    private final Player player;
    private final int steps;
    private final MovementStrategy.Direction direction;
    private final MovementStrategy movementStrategy;
    private final MapComponentVisitor visitor;
    /**
     * Construct a new command for moving the player in a single direction.
     * @param player the player to be moved
     * @param steps the number of steps to move on
     * @param direction the direction where to move (UP, DOWN, LEFT, RIGHT)
     * @param movementStrategy the strategy used to calculate and validate the movement
     * @param map the map of Cluedo game
     */
    public MoveInSingleDirection(final Player player, final int steps, final MovementStrategy.Direction direction,
                                 final MovementStrategy movementStrategy, final Map map) {
        this.player = player;
        this.steps = steps;
        this.direction = direction;
        this.movementStrategy = movementStrategy;
        this.visitor = map.getVisitor();
    }

    @Override
    public void execute() {
        final Position newPosition = movementStrategy.calculatePosition(player.getCurrentPosition(), steps, direction);
        final Square square = this.visitor.getSquareByPosition(newPosition);
        if (movementStrategy.isValidMove(player, newPosition)) {
            ((MutablePlayer) player).setCurrentSteps(player.getSteps() - 1);
            if (this.visitor.isSquareInRoom(square) && this.visitor.getRoomBySquare(square).isPresent()) {
                this.visitor.getRoomBySquare(this.visitor.getSquareByPosition(player.getCurrentPosition()))
                            .get().removePlayerFromRoom(player);
                ((MutablePlayer) player).setPosition(newPosition);
                this.visitor.getRoomBySquare(square).get().addPlayerInRoom(player);
            } else if (!this.visitor.isSquareInRoom(square)) {
                this.visitor.getSquareByPosition(player.getCurrentPosition()).removePlayer();
                ((MutablePlayer) player).setPosition(newPosition);
                this.visitor.getSquareByPosition(newPosition).landOn(player);
            } 
        } else {
            throw new IllegalArgumentException("Invalid move: the player cannot move outside the board or into an invalid area");
        }
    }

}
