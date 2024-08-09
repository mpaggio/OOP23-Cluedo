package it.unibo.cluedo.model.deck.api;

import it.unibo.cluedo.model.card.api.Card;
import java.util.List;
/**
 * Represents the deck of cards in the Cluedo game.
 */
public interface Deck {
    /**
     * Initialize the deck with the right number and type of cards.
     */
    void initializeDeck();

    /**
     * Get the solution of the game, consisting of one card for each type
     * (character, weapon, room).
     * 
     * @return the list of cards representing the solution of the game
     */
    List<Card> drawSolution();

    /**
     * Distributes the remaining cards to the players evenly.
     * 
     * @param numberOfPlayers the number of players
     * @return a list of lists of cards, where each inner list represents
     * the cards of a player 
     */
    List<List<Card>> distributedCards(int numberOfPlayers);
}
