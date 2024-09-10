package it.unibo.cluedo.model.notebook.api;

import java.io.Serializable;
import java.util.List;

/**
 * Interface that represents the notebook of the player.
 * The notebook is used to keep track of the cards that the player has seen.
 */
public interface Notebook extends Serializable {

    /**
     * Initialize the notebook with the cards that the player has in his hand.
     * @param playerCards the list of the cards that the player has in his hand.
     */
    void initialize(List<String> playerCards);

    /**
     * Log the card that the player has seen.
     * @param card
     */
    void logSeenCards(String card);

    /**
     * Get the list of the suspects that the player has seen.
     * @return the list of the suspects that the player has seen.
     */
    List<String> getSeenSuspects();

    /**
     * Get the list of the weapons that the player has seen.
     * @return the list of the weapons that the player has seen.
     */
    List<String> getSeenWeapons();

    /**
     * Get the list of the rooms that the player has seen.
     * @return the list of the rooms that the player has seen.
     */
    List<String> getSeenRooms();
}
