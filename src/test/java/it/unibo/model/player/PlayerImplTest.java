package it.unibo.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.impl.CardImpl;
import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.model.player.impl.PlayerImpl;
import it.unibo.cluedo.utilities.Position;

/**
 * Test class for a {@link PlayerImpl} class.
 */
final class PlayerImplTest {

    private static final String USERNAME = "username";
    private static final String COLOR = "color";
    private static final int MIN_DICE_ROLL = 1;
    private static final int MAX_DICE_ROLL = 6;
    private static final int MAX_DOUBLE_DICE_ROLL = 12;
    private static final int MIN_DOUBLE_DICE_ROLL = 2;
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
        assertTrue(testPlayer.canNextTurn());
        assertFalse(testPlayer.hasLost());
        assertEquals(0, testPlayer.getPlayerCards().size());
    }

    /**
     * Test the set of the player's turn.
     */
    @Test
    void testSetPlayerTurn() {
        if (testPlayer instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            mutablePlayer.setPlayerTurn(true);
            assertTrue(testPlayer.isPlayerTurn());
            mutablePlayer.setPlayerTurn(false);
            assertFalse(testPlayer.isPlayerTurn());
        }
    }

    /**
     * Test the set of the player's victory condition.
     */
    @Test
    void testHasWon() {
        if (testPlayer instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            mutablePlayer.setHasWon(true);
            assertTrue(testPlayer.hasWon());
            mutablePlayer.setHasWon(false);
            assertFalse(testPlayer.hasWon());
        }
    }

    /**
     * Test the set of the player's position in room.
     */
    @Test
    void testSetInRoom() {
        if (testPlayer instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            mutablePlayer.setInRoom(true);
            assertTrue(testPlayer.isInRoom());
            mutablePlayer.setInRoom(false);
            assertFalse(testPlayer.isInRoom());
        }
    }

    /**
     * Test th set of the player's double roll dice condition.
     */
    @Test
    void testSetDoubleRollDice() {
        if (testPlayer instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            mutablePlayer.setDoubleRollDice(true);
            assertTrue(testPlayer.canDoubleRollDice());
            mutablePlayer.setDoubleRollDice(false);
            assertFalse(testPlayer.canDoubleRollDice());
        }
    }
    /**
     * Test the set of the player's next turn condition.
     */
    @Test
    void testSetNextTurn() {
        if (testPlayer instanceof MutablePlayer) { 
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            mutablePlayer.setNextTurn(false);
            assertFalse(testPlayer.canNextTurn());
            mutablePlayer.setNextTurn(true);
            assertTrue(testPlayer.canNextTurn());
        }
    }

    /**
     * Test the set of the player's position.
     */
    @Test
    void testSetCurrentPosition() {
        if (testPlayer instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            final Position position = new Position(1, 1);
            mutablePlayer.setPosition(position);
            assertEquals(position, testPlayer.getCurrentPosition());
        }
    }

    /**
     * Test the set of the player's cards, it also cheks if the cards are exactly 3.
     */
    @Test
    void testSetPlayerCards() {
        final List<Card> cards = List.of(
            new CardImpl(Card.Type.ROOM, "card1", "src/main/resources/Kitchen.png"),
            new CardImpl(Card.Type.WEAPON, "card2", "src/main/resources/Candlestick.png"),
            new CardImpl(Card.Type.CHARACTER, "card3", "src/main/resources/ColonelMustard.png")
        );
        if (testPlayer instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            mutablePlayer.setPlayerCards(cards);
            assertEquals(3, testPlayer.getPlayerCards().size());
            assertEquals(cards, testPlayer.getPlayerCards());

        }
    }

    /**
     * Test the get of the player's steps after a roll dice.
     */
    @Test
    void testGetSteps() {
        final int steps = testPlayer.getSteps();
        assertTrue(steps >= MIN_DICE_ROLL && steps <= MAX_DICE_ROLL);
    }

    /**
     * Test the get of the player's steps after a double roll dice.
     */
    @Test
    void testGetStepsWithDoubleRollDice() {
        if (testPlayer instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            mutablePlayer.setDoubleRollDice(true);
            final int steps = testPlayer.getSteps();
            assertTrue(steps >= MIN_DOUBLE_DICE_ROLL && steps <= MAX_DOUBLE_DICE_ROLL);
        }
    }

    /**
     * Test the notebook update after the player's cards are set.
     */
    @Test
    void testNotebookUpdate() {
        final List<Card> cards = List.of(
            new CardImpl(Card.Type.ROOM, "card1", "src/main/resources/Kitchen.png"),
            new CardImpl(Card.Type.WEAPON, "card2", "src/main/resources/Candlestick.png"),
            new CardImpl(Card.Type.CHARACTER, "card3", "src/main/resources/ColonelMustard.png")
        );
        if (testPlayer instanceof MutablePlayer) {
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            mutablePlayer.setPlayerCards(cards);
            final Notebook notebook = testPlayer.getPlayerNotebook();
            assertTrue(notebook.getSeenRooms().contains("card1"));
            assertTrue(notebook.getSeenWeapons().contains("card2"));
            assertTrue(notebook.getSeenSuspects().contains("card3"));
        }
    }

    /**
     * Test the set of player's lost condition.
     */
    @Test
    void testHasLost() {
        if (testPlayer instanceof MutablePlayer) { 
            final MutablePlayer mutablePlayer = (MutablePlayer) testPlayer;
            mutablePlayer.setHasWon(false);
            assertFalse(testPlayer.hasLost());
            mutablePlayer.setHasWon(true);
            assertTrue(testPlayer.hasLost());
        }
    }
}
