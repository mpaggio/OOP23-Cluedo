package it.unibo.cluedo.controller.gamesavecontroller.api;

import java.util.Optional;
import it.unibo.cluedo.controller.gamesavecontroller.impl.GameSaveControllerImpl.GameState;

/**
 * Interface used to save and load the game.
 */
public interface GameSaveController {

    /**
     * Save the current game state in a file.
     * @param gameState the current game state
     */
    void saveGame(GameState gameState);

    /**
     * Gives the output of the saved games.
     *
     * @return an optional containing a string representing the saved games.
     */
    Optional<String> getOutputSavedGames();

    /**
     * Load a saved game.
     *
     * @return true if the game is loaded, false otherwise
     */
    Optional<GameState> loadGame();

    /**
     * Get the current game state.
     *
     * @return the current game state
     */
    GameState getCurrentGameState();
}
