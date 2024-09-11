package it.unibo.cluedo.model.deck.api;

import it.unibo.cluedo.model.card.api.Card;
import java.util.Set;
/**
 * Interface representing the deck of cards in the Cluedo game.
 */
public interface Deck {
    /**
     * Get the solution of the game, consisting of one card for each type
     * (character, weapon, room).
     * 
     * @return the set of cards representing the solution of the game
     */
    Set<Card> drawSolution();

    /**
     * Distributes the remaining cards to the players evenly.
     * 
     * @param numberOfPlayers the number of players
     * @return a set of sets of cards, where each inner list represents
     * the cards of a player 
     */
    Set<Set<Card>> distributeCards(int numberOfPlayers);

    /**
     * Get all the cards in the deck.
     * 
     * @return a set containing all the cards of the deck
     */
    Set<Card> getAllCards();

    /**
     * Get all the remaining cards in the deck.
     * 
     * @return a set containing all the remaining cards in the deck
     */
    Set<Card> getRemainingCards();
}
