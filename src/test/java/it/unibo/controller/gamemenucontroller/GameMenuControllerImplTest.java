package it.unibo.controller.gamemenucontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import it.unibo.cluedo.controller.gamemenucontroller.api.GameMenuController;
import it.unibo.cluedo.controller.gamemenucontroller.impl.GameMenuControllerImpl;
import it.unibo.cluedo.model.player.api.Player;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This class is used to test the GameMenuControllerImpl class.
 */
class GameMenuControllerImplTest {
    private GameMenuController gameMenuController;
    private static final String PLAYER1 = "Player1";
    private static final String PLAYER2 = "Player2";
    private static final String PLAYER3 = "Player3";
    private static final String RED = "RED";
    private static final String GREEN = "GREEN";
    private static final String BLUE = "BLUE";

    @BeforeEach
    void setUp() {
        gameMenuController = new GameMenuControllerImpl();
    }

    @Test
    void testStartGameWithInvalidInputs() {
        final List<String> playerUsernames = Arrays.asList(PLAYER1, PLAYER2, PLAYER3);
        final List<String> playerColors = Arrays.asList(RED, GREEN, BLUE);
        assertTrue(gameMenuController.startGame(playerUsernames, playerColors));
    }

    @Test
    void testStartGameWithInvalidNumberOfPlayers() {
        final List<String> playerUsernames = Arrays.asList(PLAYER1, PLAYER2);
        final List<String> playerColors = Arrays.asList(RED, GREEN, BLUE);
        assertFalse(gameMenuController.startGame(playerUsernames, playerColors));
    }

    @Test
    void testStartGameWithDuplicateNames() {
        final List<String> playerUsernames = Arrays.asList(PLAYER1, PLAYER1, PLAYER3);
        final List<String> playerColors = Arrays.asList(RED, GREEN, BLUE);
        assertFalse(gameMenuController.startGame(playerUsernames, playerColors));
    }

    @Test
    void testStartGameWithDuplicateColors() {
        final List<String> playerUsernames = Arrays.asList(PLAYER1, PLAYER2, PLAYER3);
        final List<String> playerColors = Arrays.asList(RED, RED, BLUE);
        assertFalse(gameMenuController.startGame(playerUsernames, playerColors));
    }


    @Test
    void testSetPlayers() {
        final List<String> playerUsernames = Arrays.asList(PLAYER1, PLAYER2, PLAYER3);
        final List<String> playerColors = Arrays.asList(RED, GREEN, BLUE);
        gameMenuController.setPlayer(playerUsernames, playerColors);
        final List<Player> players = gameMenuController.getPlayers();
        assertEquals(players.size(), 3);
        for (int i = 0; i < players.size(); i++) {
            assertEquals(playerUsernames.get(i), players.get(i).getUsername());
            assertEquals(playerColors.get(i), players.get(i).getColor());
        }
    }


    @Test
    void testGetPlayersWithouSettingPlayers() {
        final List<Player> players = gameMenuController.getPlayers();
        assertTrue(players.isEmpty());
    }

    @Test
    void testQuitGame() {
        assertDoesNotThrow(() -> gameMenuController.quitGame());
    }
}
