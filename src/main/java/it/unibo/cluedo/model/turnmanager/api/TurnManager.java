package it.unibo.cluedo.model.turnmanager.api;

import java.io.Serializable;
import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.player.api.Player;
import java.util.List;


/**
 * Interface that models the turn manager of the game.
 * It is responsible for managing the turns of the players.
 * It also verifies if the game has ended.
 */
public interface TurnManager extends Serializable {

    /**
     * Get the current player.
     * @return the current player.
     */
    Player getCurrentPlayer();

    /**
     * Remove a player from the game.
     * @param player the player to remove.
     */
    void removePlayer(Player player);

    /**
     * Advances the turn to the next player.
     */
    void switchTurn();

    /**
     * Get the current notebook.
     * @return the current notebook.
     */
    Notebook getCurrentNotebook();

    /**
     * Check if the game has ended.
     * @return a boolean indicating if the game has ended or not.
     */
    boolean isGameFinished();

    /**
     * Get the list of players.
     * @return the list of players.
     */
    List<Player> getPlayers();

    /**
     * Get the index of the current player.
     * @return the index of the current player.
     */
    int getCurrentPlayerIndex();
}
