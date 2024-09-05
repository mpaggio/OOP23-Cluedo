package it.unibo.cluedo.controller.gamesavecontroller.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface used to save and load the game.
 */
public interface GameSaveController {

    /**
     * Save the current game state in a file.
     *
     */
    void saveGame();

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
