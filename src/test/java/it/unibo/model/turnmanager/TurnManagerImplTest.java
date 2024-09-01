package it.unibo.model.turnmanager;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.turnmanager.api.TurnManager;
import it.unibo.cluedo.model.turnmanager.impl.TurnManagerImpl;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the TurnManagerImpl class.
 */
class TurnManagerImplTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private TurnManager turnManager;

    /**
     * Set up the test class.
     */
    @BeforeEach
    void setUp() {
        player1 = new MutablePlayerImpl("TestPlayer1", "Red");
        player2 = new MutablePlayerImpl("TestPlayer2", "Blue");
        player3 = new MutablePlayerImpl("TestPlayer3", "Green");

        final List<Player> players = Arrays.asList(player1, player2, player3);
        turnManager = new TurnManagerImpl(players);
    }

    /**
     * Test the initial turn of the players.
     */
    @Test
    void testInitialTurn() {
        assertTrue(((MutablePlayer) player1).isPlayerTurn());
        assertFalse(((MutablePlayer) player2).isPlayerTurn());
        assertFalse(((MutablePlayer) player3).isPlayerTurn());
    }

    /**
     * Test the switch of the turn.
     */
    @Test
    void testSwitchTurn() {
        turnManager.switchTurn();
        assertFalse(((MutablePlayer) player1).isPlayerTurn());
        assertTrue(((MutablePlayer) player2).isPlayerTurn());
        assertFalse(((MutablePlayer) player3).isPlayerTurn());

        turnManager.switchTurn();
        assertFalse(((MutablePlayer) player1).isPlayerTurn());
        assertFalse(((MutablePlayer) player2).isPlayerTurn());
        assertTrue(((MutablePlayer) player3).isPlayerTurn());
    }

    /**
     * Test the switch of the turn when a player has won.
     */
    @Test
    void testGameEndsWhenOnlyOnePlayerLeft() {
        ((MutablePlayer) player2).setHasWon(true);
        ((MutablePlayer) player3).setHasWon(true);
        turnManager.switchTurn();
        assertTrue(turnManager.isGameFinished());
        assertTrue(((MutablePlayer) player1).isPlayerTurn());
    }

    /**
     * Test the switch of the turn when more than one player has won.
     */
    @Test
    void testGameNotFinishedWhenMoreThanOnePlayerLeft() {
        ((MutablePlayer) player3).setHasWon(true);
        turnManager.switchTurn();
        assertFalse(turnManager.isGameFinished());
        assertFalse(((MutablePlayer) player1).isPlayerTurn());
        assertTrue(((MutablePlayer) player2).isPlayerTurn());
        assertFalse(((MutablePlayer) player3).isPlayerTurn());
    }

    /**
     * Test the getCurrentNotebook method.
     */
    @Test
    void testGetCurrentNotebook() {
        assertNotNull(turnManager.getCurrentNotebook());
        assertEquals(((MutablePlayer) player1).getPlayerNotebook(), turnManager.getCurrentNotebook());
    }

    /**
     * Test if the constructor throws an exception when the player list is invalid.
     */
    @Test
    void testInvalidPlayerList() {
        assertThrows(IllegalArgumentException.class, () -> new TurnManagerImpl(null));
        assertThrows(IllegalArgumentException.class, () -> new TurnManagerImpl(Arrays.asList()));
    }

    /**
     * Test the removal of the current player.
     */
    @Test
    void testRemoveCurrentPlayerAndCheckTurn() {
        turnManager.removePlayer(player1);
        turnManager.switchTurn();
        assertTrue(((MutablePlayer) player2).isPlayerTurn());
        assertFalse(((MutablePlayer) player3).isPlayerTurn());
        turnManager.switchTurn();
        assertFalse(((MutablePlayer) player2).isPlayerTurn());
        assertTrue(((MutablePlayer) player3).isPlayerTurn());
    }

    /**
     * Test the removal of one of the not current players.
     */
    @Test
    void testRemoveNonCurrentPlayerAndCheckTurn() {
        turnManager.removePlayer(player2);
        turnManager.switchTurn();
        assertFalse(((MutablePlayer) player1).isPlayerTurn());
        assertTrue(((MutablePlayer) player3).isPlayerTurn());
        turnManager.switchTurn();
        assertTrue(((MutablePlayer) player1).isPlayerTurn());
        assertFalse(((MutablePlayer) player3).isPlayerTurn());
    }

    /**
     * Test the removal of all the players.
     */
    @Test
    void testRemoveAllPlayer() {
        turnManager.removePlayer(player1);
        turnManager.removePlayer(player2);
        turnManager.removePlayer(player3);
        assertTrue(turnManager.isGameFinished());
    }

    /**
     * Test the removal of a player and the continuation of the game.
     */
    @Test
    void testRemovePlayerAndGameContinues() {
        turnManager.removePlayer(player2);
        assertFalse(turnManager.isGameFinished());
        turnManager.switchTurn();
        assertTrue(((MutablePlayer) player3).isPlayerTurn());
    }

    /**
     * Test the removal of a player that is not in the game.
     */
    @Test
    void testRemovePlayerNotInGame() {
        final Player player4 = new MutablePlayerImpl("Player4", "Yellow");
        assertThrows(IllegalArgumentException.class, () -> turnManager.removePlayer(player4));
    }

    /**
     * Test the removal of a null player.
     */
    @Test
    void testRemoveNullPlayer() {
        assertThrows(IllegalArgumentException.class, () -> turnManager.removePlayer(null));
    }

    /**
     * Test if the game ends after removing the winning player.
     */
    @Test
    void testGameEndsAfterRemovingWinningPlayer() {
        ((MutablePlayer) player1).setHasWon(true);
        ((MutablePlayer) player2).setHasWon(true);
        turnManager.removePlayer(player1);
        turnManager.removePlayer(player2);
        turnManager.switchTurn();
        assertTrue(turnManager.isGameFinished());
    }

    /**
     * Test if the game continues if more than one player remains.
     */
    @Test
    void testGameContinuesIfMoreThanOnePlayerRemains() {
        ((MutablePlayer) player3).setHasWon(true);
        turnManager.removePlayer(player3);
        assertFalse(turnManager.isGameFinished());
    }
}
