package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.cluedo.model.player.impl.PlayerImpl;
import it.unibo.cluedo.utilities.Position;

/**
 * Test class for a {@link PlayerImpl} class.
 */
final class PlayerImplTest {

    private static final String USERNAME = "username";
    private static final String COLOR = "color";
    private PlayerImpl testPlayer;

    /**
     * This is done before each test.
     */
    @BeforeEach
    void setUp() {
        testPlayer = new PlayerImpl(USERNAME, COLOR);
    }

    /**
     * Test the initialization of the player's fields.
     */
    @Test
    void testInitialization() {
        assertNotNull(testPlayer);
        assertEquals(USERNAME, testPlayer.getUsername());
        assertEquals(COLOR, testPlayer.getColor());
        assertEquals(new Position(0, 0), testPlayer.getCurrentPosition());
        assertFalse(testPlayer.isPlayerTurn());
        assertFalse(testPlayer.isInRoom());
        assertFalse(testPlayer.hasWon());
        assertFalse(testPlayer.canDoubleRollDice());
        assertFalse(testPlayer.canNextTurn());
        assertEquals(0, testPlayer.getPlayerCards().size());
    }

}
