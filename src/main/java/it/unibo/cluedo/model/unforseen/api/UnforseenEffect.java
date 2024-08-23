package it.unibo.cluedo.model.unforseen.api;

import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an effect of an Unforseen card in the Cluedo game.
 */
public interface UnforseenEffect {
    /**
     * Executes the action of the unforseen card.
     *
     * @param player the player who triggered the unforseen card
     */
    void applyEffetct(Player player);
}
