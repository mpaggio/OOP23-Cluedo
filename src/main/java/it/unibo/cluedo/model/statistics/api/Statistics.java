package it.unibo.cluedo.model.statistics.api;

import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.utilities.Pair;

import java.util.List;
import java.io.Serializable;

/**
 * Interface that represents the statistics of the game.
 */
public interface Statistics extends Serializable {
    /**
     * Method used to increment (by 1) the number of steps made by the {@link Player} passed
     * as input.
     * @param player  the moving player.
     */
    void incrementSteps(Player player);
    /**
     * Method used to increment (by 1) the number of {@link Room} visited by the {@link Player} passed
     * as input.
     * @param player  the moving player.
     */
    void incrementRoomsVisited(Player player);
    /**
     * Method used to increment (by 1) the number of {@link Card} viewd by the {@link Player} passed
     * as input.
     * @param player the player viewing the cards.
     */
    void incrementViewedCards(Player player);
    /**
     * Method used to increment (by 1) the number of number of {@link Accusation} made by the {@link Player}
     * passed as input.
     * @param player the player making the accusation.
     */
    void incrementAccusationsMade(Player player);
    /**
     * Get the number of steps made by each {@link Player} in a descending order.
     * @return a {@link Pair} containing a list of each player and the steps made.
     */
    Pair<List<Player>, List<Integer>> getStepsMade();
    /**
     * Get the number of {@link Room} visited by each {@link Player} in a 
     * descending order.
     * @return a {@link Pair} containing a list of each player and the rooms visited.
     */
    Pair<List<Player>, List<Integer>> getRoomsVisited();
    /**
     * Get the number of {@link Card} viewed by each {@link Player} in a
     * descending order.
     * @return a {@link Pair} containing a list of each player and the cards viewed.
     */
    Pair<List<Player>, List<Integer>> getViewedCards();
    /**
     * Get the number of accusations made by each {@link Player} in a descending order.
     * @return a {@link Pair} containing a list of each player and the accusations made.
     */
    Pair<List<Player>, List<Integer>> getAccusationsMade();
}
