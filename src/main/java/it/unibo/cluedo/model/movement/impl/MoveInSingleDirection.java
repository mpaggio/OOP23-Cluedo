package it.unibo.cluedo.model.movement.impl;

import it.unibo.cluedo.model.board.api.Board;
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
    private final Board map;

    /**
     * Construct a new command for moving the player in a single direction.
     * @param player the player to be moved
     * @param steps the number of steps to move on
     * @param direction the direction where to move (UP, DOWN, LEFT, RIGHT)
     * @param movementStrategy the strategy used to calculate and validate the movement
     * @param map the map of Cluedo game
     */
    public MoveInSingleDirection(final Player player, final int steps, final MovementStrategy.Direction direction,
                                 final MovementStrategy movementStrategy, final Board map) {
        this.player = player;
        this.steps = steps;
        this.direction = direction;
        this.movementStrategy = movementStrategy;
        this.map = map;
    }

    @Override
    public void execute() {
        final Position newPosition = movementStrategy.calculatePosition(player.getCurrentPosition(), steps, direction);
        final Square square = this.map.getSquareByPosition(newPosition);
        if (movementStrategy.isValidMove(player, newPosition)) {
            if (this.player instanceof MutablePlayer) {
                ((MutablePlayer) player).setCurrentSteps(player.getSteps() - 1);
            }
            if (this.map.isSquareInRoom(square) && this.map.getRoomBySquare(square).isPresent()) {
                this.map.getRoomBySquare(this.map.getSquareByPosition(player.getCurrentPosition()))
                            .get().removePlayerFromRoom(player);
                if (this.player instanceof MutablePlayer){
                    ((MutablePlayer) player).setPosition(newPosition);
                }
                this.map.getRoomBySquare(square).get().addPlayerInRoom(player);
            } else if (!this.map.isSquareInRoom(square)) {
                this.map.getSquareByPosition(player.getCurrentPosition()).removePlayer();
                if (this.player instanceof MutablePlayer) {
                    ((MutablePlayer) player).setPosition(newPosition);
                }
                this.map.getSquareByPosition(newPosition).landOn(player);
            } 
        } else {
            throw new IllegalArgumentException("Invalid move: the player cannot move outside the board or into an invalid area");
        }
    }

}
