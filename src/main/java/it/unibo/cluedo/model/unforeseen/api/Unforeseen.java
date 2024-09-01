package it.unibo.cluedo.model.unforeseen.api;

import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an unforeseen card in the Cluedo game.
 */
public interface Unforeseen {

    /**
     * Apply the unforeseen card's effect to the given player.
     *
     * @param player the player who triggered the unforeseen card
     */
    void apply(Player player);

    /**
     * Returns the description of the unforeseen card.
     * @return the description of the unforeseen card
     */
    String getDescription();
}
