package it.unibo.cluedo.model.square.api;

import java.util.Optional;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Position;

/**
 * Represents a single square of the game table.
 * Each square can have an effect that is applied when a player lands on it.
 */
public interface Square {
    /**
     * Returns the position (coordinates x and y) of the square.
     * 
     * @return the position of the square
     */
    Position getPosition();

    /**
     * Applies the square's effect to the specified player.
     * 
     * @param player the player landing on the square
     */
    void landOn(Player player);

    /**
     * Returns the effect of the square.
     * 
     * @return the effect of the square
     */
    Effect getEffect();

    /**
     * Remove the presence of the player in the square.
     */
    void removePlayer();

    /**
     * Says wether there is a player located in the square.
     * 
     * @return true if there is a player in the square
     * false otherwise
     */
    boolean isAlreadyOccupied();

    /**
     * Return the player located in the square (if present).
     * 
     * @return an optional containing the Player located in the square, if present
     */
    Optional<Player> getPlayer();
}
