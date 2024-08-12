package it.unibo.cluedo.model.square.api;

import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an effect applyable to a player.
 */
public interface Effect {
    /**
     * Applies the effect to the specified player.
     * 
     * @param player the player to whom the effect is applied
     */
    void apply(Player player);
}
