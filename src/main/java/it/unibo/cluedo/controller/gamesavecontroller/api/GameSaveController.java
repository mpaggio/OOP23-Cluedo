package it.unibo.cluedo.controller.gamesavecontroller.api;

import java.util.List;
import java.util.Optional;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.component.api.MapComponent;
import it.unibo.cluedo.model.component.api.MapComponentVisitor;

/**
 * Interface used to save and load the game.
 */
public interface GameSaveController {

    /**
     * Save the current game state in a file.
     *
     * @param players the list of players in the game.
     * @param map the list of the map components.
     * @param visitor the visitor used to save the map components.
     */
    void saveGame(List<Player> players, List<MapComponent> map, MapComponentVisitor visitor);

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
