package it.unibo.cluedo.controller.gamesavecontroller.api;

import java.util.Optional;
import it.unibo.cluedo.controller.gamesavecontroller.impl.GameSaveControllerImpl.GameState;
import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.turnmanager.api.TurnManager;
import it.unibo.cluedo.utilities.TurnFase;
import java.util.List;
import java.util.Set;

/**
 * Interface used to save and load the game.
 */
public interface GameSaveController {

    /**
     * Save the current game state in a file.
     * @param players the list of players
     * @param solution the solution of the game
     * @param turnManager the turn manager
     * @param statistics the statistics
     * @param map the board
     * @param allCards the set of all cards
     * @param turnFase the turn fase
     * @param path the path to the saving file
     */
    void saveGame(List<Player> players, Set<Card> solution, TurnManager turnManager,
        Statistics statistics, Board map, Set<Card> allCards, TurnFase turnFase, String path);

    /**
     * Load a saved game.
     * @param path the path to the saving file
     * @return true if the game is loaded, false otherwise
     */
    Optional<GameState> loadGame(String path);
}
