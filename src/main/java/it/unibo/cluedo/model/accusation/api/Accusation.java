package it.unibo.cluedo.model.accusation.api;

import java.util.Optional;
import java.util.Set;

import it.unibo.cluedo.model.card.api.Card;

/**
 * Represents a player accusation in the Cluedo game.
 */

public interface Accusation {
    /**
     * Make an accusation.
     * @param weapon  the weapon card
     * @param room the room card
     * @param character the character card
     * @param playerCards the cards of a player
     * have one of the chosen cards
     * @return an Optional containing one of the chosen cards if the player posseses it.
     */
    Optional<Card> accuse(Card weapon, Card room, Card character, Set<Card> playerCards);
    /**
     * Make the final accusation.
     * @param weapon  the weapon card
     * @param room the room card
     * @param character the character card
     * @param solution the solution of the current game
     * @return true if the player has guessed the right cards.
     */
    boolean finalAccuse(Card weapon, Card room, Card character, Set<Card> solution);

}
