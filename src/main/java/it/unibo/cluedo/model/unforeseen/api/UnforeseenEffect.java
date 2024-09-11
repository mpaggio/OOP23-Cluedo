package it.unibo.cluedo.model.unforeseen.api;

import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an effect of an Unforeseen in the Cluedo game.
 */
public interface UnforeseenEffect {

    /**
     * Executes the action of the unforeseen.
     * @param player the player who triggered the unforeseen.
     */
    void applyEffect(Player player);

    /**
     * Gets the type of the unforeseen.
     * @return the type of the unforeseen.
     */
    String getType();

    /**
     * Gets the description of the unforeseen.
     * @return the description of the unforeseen.
     */
    String getDescription();
}
