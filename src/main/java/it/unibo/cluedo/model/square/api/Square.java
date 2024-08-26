package it.unibo.cluedo.model.square.api;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.room.api.MapComponent;
import it.unibo.cluedo.utilities.Position;

/**
 * Represents a single square of the game table.
 * Each square can have an effect that is applied when a player lands on it.
 */
public interface Square extends MapComponent {
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
     * Sets that the square is occupied by the specified player.
     * 
     * @param player the player to set
     */
    void setPlayer(Player player);

    /**
     * Says wether there is a player located in the square.
     * 
     * @return true if there is a player in the square
     * false otherwise
     */
    boolean hasPlayer();
}
