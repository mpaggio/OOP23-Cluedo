package it.unibo.cluedo.model.unforseen.api;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an event card in the Cluedo game.
 */
public interface Unforseen {
    /**
     * Executes the action of the event card.
     *
     * @param player the player who triggered the event card
     */
    void applyEffetct(Player player);

    /**
     * Returns the name of the event card.
     * @return the name of the event card
     */
    String getName();
}
