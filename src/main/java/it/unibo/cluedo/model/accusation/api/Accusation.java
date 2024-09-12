package it.unibo.cluedo.model.accusation.api;

import java.util.Optional;
import java.util.Set;

import it.unibo.cluedo.model.card.api.Card;

/**
 * Represents a player accusation in the Cluedo game.
 */

public interface Accusation {
    /**
     * Method to make an accusation in the game. The {@link Player} must choose three {@link Crad}
     * of different types and the method will check if one of the other players has one of them.
     * @param weapon  the weapon card
     * @param room the room card
     * @param character the character card
     * @param playerCards a card {@link Set} of a player
     * @return an {@link Optional} containing one of the chosen cards if the player posseses it.
     */
    Optional<Card> accuse(Card weapon, Card room, Card character, Set<Card> playerCards);
    /**
     *  Method to make the final accusation in the game. The {@link Player} must choose three {@link Crad}
     * of different types and the method will check if the {@link Set} of cards representing the
     * solution of the game contains the chosen cards.
     * @param weapon  the weapon card
     * @param room the room card
     * @param character the character card
     * @param solution the solution of the current game
     * @return true if the player has guessed the right cards.
     */
    boolean finalAccuse(Card weapon, Card room, Card character, Set<Card> solution);

}
