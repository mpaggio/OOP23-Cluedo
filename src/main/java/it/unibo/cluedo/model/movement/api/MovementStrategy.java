package it.unibo.cluedo.model.movement.api;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Position;

/**
 * Interface that defines the strategy for moving players on the board, including
 * calculating new positions and validating movement.
 */
public interface MovementStrategy {
    /**
     * Calculates the new position of the player based on the current position the number of steps, and the direction of movement.
     * @param currentPosition the current position of the player
     * @param steps the number of steps the player should move
     * @param direction the direction where the player should move (UP,DOWN,LEFT,RIGHT)
     * @return the new position after the moving
     */
    Position calculatePosition(Position currentPosition, int steps, Direction direction);

    /**
     * Checks if a move is valid.
     * @param player the player who wants to move
     * @param newPosition the new position the player wants to move to
     * @return true if the move is valid,false otherwise
     */
    boolean isValidMove(Player player, Position newPosition);

    /**
     * Checks if a player can move through the trapdoor.
     * @param player the player who wants to move
     * @return true if the player can move, false otherwise
     */
    boolean isTrapDoorUsable(Player player);

    /**
     * Checks if a player has entered a room.
     * @param player the player who wants to move
     * @param newPosition the new position where the player wants to move
     * @return true if the player has entered a room, false otherwise
     */
    boolean hasPlayerEnteredInRoom(Player player, Position newPosition);

    /**
     * Enum that represents the possible directions a player can move.
     */
    enum Direction {
        /**
         * Up direction.
         */
        UP,
        /**
         * Down direction.
         */
        DOWN,
        /**
         * Right direction.
         */
        RIGHT,
        /**
         * Left direction.
         */
        LEFT
    }
}
