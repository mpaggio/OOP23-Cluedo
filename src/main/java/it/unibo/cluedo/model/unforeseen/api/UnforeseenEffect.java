package it.unibo.cluedo.model.unforeseen.api;

import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an effect of an Unforeseen card in the Cluedo game.
 */
public interface UnforeseenEffect {
    /**
     * Executes the action of the unforeseen card.
     *
     * @param player the player who triggered the unforeseen card
     */
    void applyEffect(Player player);
}
