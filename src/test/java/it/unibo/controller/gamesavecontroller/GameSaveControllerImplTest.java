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

/**
 * This class is used to test the GameSaveControllerImpl class.
 */
class GameSaveControllerImplTest {

    private static final String FILE_NAME = "saved_game.ser";
    private GameSaveController gameSaveController;
    private GameSaveControllerImpl.GameState gameState;
    private List<Player> players;
    private Board map;
    private int currentPlayerIndex;


    @BeforeEach
    void setUp() {
        gameSaveController = new GameSaveControllerImpl();
        players = new ArrayList<>();
        map = new BoardImpl();
        currentPlayerIndex = 0;
        gameState = new GameSaveControllerImpl.GameState(players, map, currentPlayerIndex);
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
        gameSaveController.saveGame(gameState);
        final File file = new File(FILE_NAME);
        assertTrue(file.exists());
    }

    /**
     * Test the getOutputSavedGames method.
     */
    @Test
    void testGetOutputSavedGames() {
        gameSaveController.saveGame(gameState);
        final Optional<String> output = gameSaveController.getOutputSavedGames();
        assertTrue(output.isPresent());
        assertEquals("A saved game is available", output.get());
    }

    /**
     * Test the loadGame method.
     */
    @Test
    void testLoadGame() {
        gameSaveController.saveGame(gameState);
        final Optional<GameSaveControllerImpl.GameState> loadedGameState = gameSaveController.loadGame();
        assertTrue(loadedGameState.isPresent());
        assertEquals(gameState.getPlayers(), loadedGameState.get().getPlayers());
        assertEquals(gameState.getMap(), loadedGameState.get().getMap());
        assertEquals(gameState.getCurrentPlayerIndex(), loadedGameState.get().getCurrentPlayerIndex());
    }

    /**
     * Test the getCurrentGameState method.
     */
    @Test
    void testGetCurrentGameState() {
        final GameSaveControllerImpl.GameState currentState = gameSaveController.getCurrentGameState();
        assertEquals(players, currentState.getPlayers());
        assertEquals(map, currentState.getMap());
        assertEquals(currentPlayerIndex, currentState.getCurrentPlayerIndex());
    }
}
