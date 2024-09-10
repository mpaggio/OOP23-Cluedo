package it.unibo.cluedo.model.square.api;

import java.util.Optional;
import java.io.Serializable;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Position;

/**
 * Represents a single square of the game table.
 * Each square can have an effect that is applied when a player lands on it.
 */
public interface Square extends Serializable {
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
     * Sets the presence of the specified player in the square.
     * 
     * @param player the player to be set
     */
    void setPlayer(Player player);

    /**
     * Sets that the square is made for entering in a room.
     */
    void setIsForEntrance();

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

    /**
     * Returns if the square is made for entering in a room or not.
     * 
     * @return true if the square is made for entering in a room, false otherwise
     */
    boolean isForEntrance();

    /**
     * Returns the player effected by the square effect in the current turn.
     * 
     * @return the player effected by the square effect in the current turn, if present
     */
    Optional<Player> getEffectedPlayer();

    /**
     * Reset the player effected by the square effect in the current turn.
     */
    void resetEffectedPlayer();
}
