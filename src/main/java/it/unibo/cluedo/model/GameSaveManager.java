package it.unibo.cluedo.model;

import java.util.List;
import java.util.Optional;
import it.unibo.cluedo.model.player.api.Player;

/**
 * Interface used to save and load the game.
 */
public interface GameSaveManager {

    /**
     * Save the current game state in a file.
     *
     * @param players the list of players in the game.
     */
    void saveGame(List<Player> players);

    /**
     * Shows the data of the saved games.
     *
     * @return the list of the saved games.
     */
    List<String> viewSavedGames();

    /**
     * Gives the output of the saved games.
     *
     * @return an optional containing a string representing the saved games.
     */
    Optional<String> getOutputSavedGames();
}