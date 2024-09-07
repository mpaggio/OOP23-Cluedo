package it.unibo.controller.gamesavecontroller;

import it.unibo.cluedo.model.player.api.Player;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import it.unibo.cluedo.controller.gamesavecontroller.impl.GameSaveControllerImpl;
import it.unibo.cluedo.controller.gamesavecontroller.api.GameSaveController;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.statistics.api.Statistics;
import it.unibo.cluedo.model.turnmanager.api.TurnManager;
import it.unibo.cluedo.utilities.TurnFase;
import java.util.Set;
import java.nio.file.Paths;

/**
 * This class is used to test the GameSaveControllerImpl class.
 */
class GameSaveControllerImplTest {

    private static final String FILE_NAME = Paths.get("src", "main", "resources", "saves", "saved_game.ser").toString();
    private GameSaveController gameSaveController;
    private GameSaveControllerImpl.GameState gameState;
    private List<Player> players;
    private Set<Card> solution;
    private TurnManager turnManager;
    private Statistics statistics;
    private Board map;
    private Set<Card> allCards;
    private TurnFase turnFase;


    @BeforeEach
    void setUp() {
        gameSaveController = new GameSaveControllerImpl();
        players = new ArrayList<>();
        solution = Set.of();
        turnManager = null;
        statistics = null;
        map = new BoardImpl();
        allCards = Set.of();
        turnFase = null;
        gameState = new GameSaveControllerImpl.GameState(players, solution, turnManager, statistics, map, allCards, turnFase);
    }

    @AfterEach
    void tearDown() {
        final File file = new File(FILE_NAME);
        if (file.exists() && !file.delete()) {
            throw new IllegalStateException("Cannot delete the file " + FILE_NAME);
        }
    }

    /**
     * Test the saveGame method.
     */
    @Test
    void testSaveGame() {
        gameSaveController.saveGame(players, solution, turnManager, statistics, map, allCards, turnFase);
        final File file = new File(FILE_NAME);
        assertTrue(file.exists());
    }

    /**
     * Test the loadGame method.
     */
    @Test
    void testLoadGame() {
        gameSaveController.saveGame(players, solution, turnManager, statistics, map, allCards, turnFase);
        final Optional<GameSaveControllerImpl.GameState> loadedGameState = gameSaveController.loadGame();
        assertTrue(loadedGameState.isPresent());
        assertEquals(gameState.getPlayers(), loadedGameState.get().getPlayers());
        assertEquals(gameState.getSolution(), loadedGameState.get().getSolution());
        assertEquals(gameState.getTurnManager(), loadedGameState.get().getTurnManager());
        assertEquals(gameState.getStatistics(), loadedGameState.get().getStatistics());
        assertEquals(gameState.getMap(), loadedGameState.get().getMap());
        assertEquals(gameState.getAllCards(), loadedGameState.get().getAllCards());
        assertEquals(gameState.getTurnFase(), loadedGameState.get().getTurnFase());
    }
}
