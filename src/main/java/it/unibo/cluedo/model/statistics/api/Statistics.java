package it.unibo.cluedo.model.statistics.api;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Pair;

import java.util.List;

/**
 * This interface represents the statistics of the game.
 */

public interface Statistics {
    /**
     * Increment the number of steps made by the player.
     * @param player  the moving player.
     * @param steps the number of steps made by the player in this turn.
     */
    void incrementSteps(Player player, int steps);
    /**
     * Increment the number of rooms visited by the player.
     * @param player  the moving player.
     */
    void incrementRoomsVisited(Player player);
    /**
     * Increment the number of cards viewed by the player.
     * @param player the player viewing the cards.
     */
    void incrementViewedCards(Player player);
    /**
     * Increment the number of accusations by the player.
     * @param player the player making the accusation.
     */
    void incrementAccusationsMade(Player player);
    /**
     * Get the number of steps made by each player in a descending order.
     * @return a pair containing a list of each player and the steps made.
     */
    Pair<List<Player>, List<Integer>> getStepsMade();
    /**
     * Get the number of rooms visited by each player in a 
     * descending order.
     * @return a pair containing a list of each player and the rooms visited.
     */
    Pair<List<Player>, List<Integer>> getRoomsVisited();
    /**
     * Get the number of cards viewed by each player in a
     * descending order.
     * @return a pair containing a list of each player and the cards viewed.
     */
    Pair<List<Player>, List<Integer>> getViewedCards();
    /**
     * Get the number of accusations made by each player in a descending order.
     * @return a pair containing a list of each player and the accusations made.
     */
    Pair<List<Player>, List<Integer>> getAccusationsMade();
}
