package it.unibo.cluedo.controller.gamesavecontroller.api;

import java.util.List;
import java.util.Optional;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.board.api.Board;

/**
 * Interface used to save and load the game.
 */
public interface GameSaveController {

    /**
     * Save the current game state in a file.
     *
     * @param players the list of players in the game.
     * @param map the list of the map components.
     * @param currentPlayerIndex the index of the current player.
     */
    void saveGame(List<Player> players, Board map, int currentPlayerIndex);

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
