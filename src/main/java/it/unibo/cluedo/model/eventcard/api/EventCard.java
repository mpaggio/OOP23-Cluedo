package it.unibo.cluedo.model.eventcard.api;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that represents an event card in the Cluedo game.
 */
public interface EventCard extends Card {
    /**
     * Executes the action of the event card.
     *
     * @param player the player who triggered the event card
     */
    void executeAction(Player player);
}
