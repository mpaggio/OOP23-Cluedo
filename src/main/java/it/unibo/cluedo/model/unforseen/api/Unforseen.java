package it.unibo.cluedo.model.unforseen.api;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an unforseen card in the Cluedo game.
 */
public interface Unforseen {
    /**
     * Apply the unforseen card's effect to the given player.
     *
     * @param player the player who triggered the unforseen card
     */
    void apply(Player player);

    /**
     * Returns the description of the event card.
     * @return the description of the event card
     */
    String getDescription();
}
