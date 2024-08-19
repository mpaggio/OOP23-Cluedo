package it.unibo.cluedo.model.accusation.api;

import java.util.Optional;

import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Represents a player accusation in the Cluedo game.
 */

public interface Accusation {
    /**
     * Make an accusation.
     * @param weapon  the weapon card
     * @param room the room card
     * @param character the character card
     * @param player one of the player in the game who might
     * have one of the chosen cards
     * @return an Option containing one of the chosen cards if the player posseses it.
     */
    Optional<Card> accuse(Card weapon, Card room, Card character, Player player);
    /**
     * Make the final accusation.
     * @param weapon  the weapon card
     * @param room the room card
     * @param character the character card
     * @return true if the player has guessed the right cards.
     */
    boolean finalAccuse(Card weapon, Card room, Card character);

}
