package it.unibo.cluedo.model.turnmanager.api;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface that models the turn manager of the game.
 */

public interface TurnManager {
    /**
     * Get the current player.
     * 
     * @return the current player.
     */
    Player getCurrentPlayer();

    /**
     * Advances the turn to the next player.
     */
    void switchTurn();

    // Notebook getCurrentNotebook();
}
