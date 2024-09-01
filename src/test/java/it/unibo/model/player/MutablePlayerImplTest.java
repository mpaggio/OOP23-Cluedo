package it.unibo.model.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.utilities.Position;
import java.util.List;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.card.impl.CardImpl;

/**
 * Test class for a {@link MutablePlayerImpl} class.
 */
final class MutablePlayerImplTest {

    private MutablePlayerImpl player;
    private static final String PLAYER_1 = "Player1";
    private static final String COLOR_RED = "Red";
    private static final int MIN_STEPS = 1;
    private static final int MAX_STEPS = 6;
    private static final int INITIAL_STEPS = 5;

    /**
     * This is done before each test.
     */
    @BeforeEach
    void setUp() {
        player = new MutablePlayerImpl(PLAYER_1, COLOR_RED);
    }

    /**
     * Test the initial values of player's fields.
     */
    @Test
    void testInitialValues() {
        assertEquals(PLAYER_1, player.getUsername());
        assertEquals(COLOR_RED, player.getColor());
        assertTrue(Position.getDefaultPositions().contains(player.getCurrentPosition()));
        assertFalse(player.isPlayerTurn());
        assertFalse(player.hasWon());
        assertFalse(player.hasLost());
        assertFalse(player.isInRoom());
        assertFalse(player.canDoubleRollDice());
        assertTrue(player.canNextTurn());
    }

    /**
     * Test the set and get of player's position.
     */
    @Test
    void testSetAndGetPosition() {
        final Position position = new Position(10, 10);
        player.setPosition(position);
        assertEquals(position, player.getCurrentPosition());
    }

    /**
     * Test the set and of player's turn.
     */
    @Test
    void testSetAndIsPlayerTurn() {
        player.setPlayerTurn(true);
        assertTrue(player.isPlayerTurn());
        player.setPlayerTurn(false);
        assertFalse(player.isPlayerTurn());
    }

    /**
     * Test the set of player's win condition.
     */
    @Test
    void testSetAndHasWon() {
        player.setHasWon(true);
        assertTrue(player.hasWon());
        player.setHasWon(false);
        assertFalse(player.hasWon());
    }

    /**
     * Test the set of player's lost condition.
     */
    @Test
    void testSetAndHasLost() {
        player.setHasLost(true);
        assertTrue(player.hasLost());
        player.setHasLost(false);
        assertFalse(player.hasLost());
    }

    /**
     * Test the set of a player in a room.
     */
    @Test
    void testSetAndIsInRoom() {
        player.setInRoom(true);
        assertTrue(player.isInRoom());
        player.setInRoom(false);
        assertFalse(player.isInRoom());
    }

    /**
     * Test the set of player's double roll dice condition.
     */
    @Test
    void testSetAndCanDoubleRollDice() {
        player.setDoubleRollDice(true);
        assertTrue(player.canDoubleRollDice());
        player.setDoubleRollDice(false);
        assertFalse(player.canDoubleRollDice());
    }

    /**
     * Test the set of player's next turn condition.
     */
    @Test
    void testSetAndCanNextTurn() {
        player.setNextTurn(true);
        assertTrue(player.canNextTurn());
        player.setNextTurn(false);
        assertFalse(player.canNextTurn());
    } 

    /**
     * Test the set of player's cards.
     */
    @Test
    void testSetPlayerCards() {
        final List<Card> cards = List.of(
            new CardImpl(Card.Type.ROOM, "card1", "src/main/resources/Kitchen.png"),
            new CardImpl(Card.Type.WEAPON, "card2", "src/main/resources/Candlestick.png"),
            new CardImpl(Card.Type.CHARACTER, "card3", "src/main/resources/ColonelMustard.png")
        );
        player.setPlayerCards(cards);
        assertEquals(cards, player.getPlayerCards());
    }

    /**
     * Test the set of player's current steps.
     */
    @Test
    void testSetAndGetCurrentSteps() {
        player.setCurrentSteps(INITIAL_STEPS);
        assertEquals(INITIAL_STEPS, player.getCurrentSteps());
    }

    /**
     * Test the roll dice and get steps.
     */
    @Test
    void testRollDiceAndGetSteps() {
        final int steps = player.getSteps();
        assertTrue(steps >= MIN_STEPS && steps <= MAX_STEPS);

    }
}
