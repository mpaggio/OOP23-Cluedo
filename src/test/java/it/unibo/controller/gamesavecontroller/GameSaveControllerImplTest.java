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
import it.unibo.cluedo.model.turnmanager.impl.TurnManagerImpl;
import it.unibo.cluedo.model.player.impl.PlayerImpl;
import it.unibo.cluedo.model.room.api.Room;
import it.unibo.cluedo.model.square.api.Square;
import it.unibo.cluedo.utilities.TurnFase;
import java.util.Set;

/**
 * This class is used to test the GameSaveControllerImpl class.
 */
class GameSaveControllerImplTest {

    private static final String TEST_FILE_NAME = System.getProperty("user.home") + "/test_saved_game.ser";
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
        final Player player1 = new PlayerImpl("Player1", "Red");
        players.add(player1);
        solution = Set.of();
        turnManager = new TurnManagerImpl(players);
        statistics = null;
        map = new BoardImpl();
        allCards = Set.of();
        turnFase = null;
        gameState = new GameSaveControllerImpl.GameState(players, solution, turnManager, statistics, map, allCards, turnFase);
    }

    @AfterEach
    void tearDown() {
        final File file = new File(TEST_FILE_NAME);
        if (file.exists() && !file.delete()) {
            throw new IllegalStateException("Cannot delete the file " + TEST_FILE_NAME);
        }
    }

    /**
     * Test the saveGame method.
     */
    @Test
    void testSaveGame() {
        gameSaveController.saveGame(players, solution, turnManager, statistics, map, allCards, turnFase, TEST_FILE_NAME);
        final File file = new File(TEST_FILE_NAME);
        assertTrue(file.exists());
    }

    /**
     * Test the loadGame method.
     */
    @Test
    void testLoadGame() {
        gameSaveController.saveGame(players, solution, turnManager, statistics, map, allCards, turnFase, TEST_FILE_NAME);
        final Optional<GameSaveControllerImpl.GameState> loadedGameState = gameSaveController.loadGame(TEST_FILE_NAME);
        assertTrue(loadedGameState.isPresent());
        assertEquals(gameState.getPlayers().get(0).getUsername(), loadedGameState.get().getPlayers().get(0).getUsername());
        assertEquals(gameState.getSolution(), loadedGameState.get().getSolution());

        final TurnManager expectedTurnManager = gameState.getTurnManager();
        final TurnManager loadedTurnManager = loadedGameState.get().getTurnManager();
        assertEquals(expectedTurnManager.getPlayers().get(0).getUsername(), loadedTurnManager.getPlayers().get(0).getUsername());
        assertEquals(expectedTurnManager.getCurrentPlayerIndex(), loadedTurnManager.getCurrentPlayerIndex());
        assertEquals(expectedTurnManager.isGameFinished(), loadedTurnManager.isGameFinished());

        assertEquals(gameState.getStatistics(), loadedGameState.get().getStatistics());

        final Board expectedMap = gameState.getMap();
        final Board loadedMap = loadedGameState.get().getMap();
        final List<Room> expectedRooms = expectedMap.getRooms();
        final List<Room> loadedRooms = loadedMap.getRooms();
        final List<Square> expectedSquares = expectedMap.getSquares();
        final List<Square> loadedSquares = loadedMap.getSquares();

        for (int i = 0; i < expectedRooms.size(); i++) {
            assertEquals(expectedRooms.get(i).getName(), loadedRooms.get(i).getName());
        }
        for (int i = 0; i < expectedSquares.size(); i++) {
            assertEquals(expectedSquares.get(i).getPosition(), loadedSquares.get(i).getPosition());
        }

        assertEquals(gameState.getAllCards(), loadedGameState.get().getAllCards());
        assertEquals(gameState.getTurnFase(), loadedGameState.get().getTurnFase());
    }
}
