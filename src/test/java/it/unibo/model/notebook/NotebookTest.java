package it.unibo.model.notebook;

import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.notebook.impl.NotebookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the Notebook class.
 */
class NotebookTest {
    private Notebook notebook;
    private static final String PROFESSOR_PLUM = "Professor Plum";
    private static final String CANDLESTICK = "Candlestick";
    private static final String KITCHEN = "Kitchen";
    private static final String MISS_SCARLET = "Miss Scarlet";

    /**
     * Set up the test environment by initializing the Notebook with a list of suspects,weapons and rooms.
     */
    @BeforeEach
    void setUp() {
        final List<String> allSuspects = Arrays.asList(PROFESSOR_PLUM, MISS_SCARLET, "Colonel Mustard");
        final List<String> allWeapons = Arrays.asList(CANDLESTICK, "Revolver", "Rope");
        final List<String> allRooms = Arrays.asList(KITCHEN, "Ballroom", "Conservatory");
        notebook = new NotebookImpl(allSuspects, allWeapons, allRooms);
    }

    /**
     * Tests the automatic initialization of the notebook with the player's starting cards.
     * Ensures that the cards owned by the player are marked as seen.
     */
    @Test
    void testInitialize() {
        final List<String> playerCards = Arrays.asList(PROFESSOR_PLUM, "Revolver", KITCHEN);
        notebook.initialize(playerCards);

        final List<String> unselectedSuspects = notebook.getUnselectedSuspects();
        final List<String> unselectedWeapons = notebook.getUnselectedWeapons();
        final List<String> unselectedRooms = notebook.getUnselectedRooms();

        assertFalse(unselectedSuspects.contains(PROFESSOR_PLUM));
        assertFalse(unselectedWeapons.contains("Revolver"));
        assertFalse(unselectedRooms.contains(KITCHEN));

        assertEquals(2, unselectedSuspects.size());
        assertEquals(2, unselectedWeapons.size());
        assertEquals(2, unselectedRooms.size());
    }

    /**
     * Tests the logSeenCard method by simulating the player seeing a card during the game.
     * Ensures that the seen card is marked as seen in the notebook.
     */
    @Test
    void testLogSeenCards() {
        notebook.logSeenCards(MISS_SCARLET);
        notebook.logSeenCards(CANDLESTICK);
        notebook.logSeenCards("Ballroom");

        final List<String> unselectedSuspects = notebook.getUnselectedSuspects();
        final List<String> unselectedWeapons = notebook.getUnselectedWeapons();
        final List<String> unselectedRooms = notebook.getUnselectedRooms();

        assertFalse(unselectedSuspects.contains(MISS_SCARLET));
        assertFalse(unselectedWeapons.contains(CANDLESTICK));
        assertFalse(unselectedRooms.contains("Ballroom"));

        assertEquals(2, unselectedSuspects.size());
        assertEquals(2, unselectedWeapons.size());
        assertEquals(2, unselectedRooms.size());
    }

    /**
     * Tests the combination of initialize and logSeenCard methods.
     * Ensures that both methods work together to keep track of seen cards.
     */
    @Test
    void testInitializeAndLogSuspects() {
        final List<String> playerCards = Arrays.asList("Colonel Mustard", "Rope", "Conservatory");
        notebook.initialize(playerCards);

        notebook.logSeenCards(MISS_SCARLET);
        notebook.logSeenCards(CANDLESTICK);

        final List<String> unselectedSuspects = notebook.getUnselectedSuspects();
        final List<String> unselectedWeapons = notebook.getUnselectedWeapons();
        final List<String> unselectedRooms = notebook.getUnselectedRooms();

        assertFalse(unselectedSuspects.contains("Colonel Mustard"));
        assertFalse(unselectedSuspects.contains(MISS_SCARLET));
        assertFalse(unselectedWeapons.contains("Rope"));
        assertFalse(unselectedWeapons.contains(CANDLESTICK));
        assertFalse(unselectedRooms.contains("Conservatory"));

        assertEquals(1, unselectedSuspects.size());
        assertEquals(1, unselectedWeapons.size());
        assertEquals(2, unselectedRooms.size());

    }
}
