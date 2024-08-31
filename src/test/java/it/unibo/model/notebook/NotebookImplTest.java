package it.unibo.model.notebook;

import it.unibo.cluedo.model.notebook.api.Notebook;
import it.unibo.cluedo.model.notebook.impl.NotebookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the Notebook class.
 */
class NotebookImplTest {

    private Notebook notebook;
    private static final String PROFESSOR_PLUM = "Professor Plum";
    private static final String CANDLESTICK = "Candlestick";
    private static final String KITCHEN = "Kitchen";
    private static final String MISS_SCARLET = "Miss Scarlet";
    private static final String REVOLVER = "Revolver";
    private static final String BALLROOM = "Ballroom";

    /**
     * Set up the test environment by initializing the Notebook with a list of suspects,weapons and rooms.
     */
    @BeforeEach
    void setUp() {
        notebook = new NotebookImpl();
    }

    /**
     * Tests the automatic initialization of the notebook with the player's starting cards.
     * Ensures that the cards owned by the player are marked as seen.
     */
    @Test
    void testInitialize() {
        final List<String> playerCards = Arrays.asList(PROFESSOR_PLUM, REVOLVER, KITCHEN);
        notebook.initialize(playerCards);

        final List<String> seenSuspects = notebook.getSeenSuspects();
        final List<String> seenWeapons = notebook.getSeenWeapons();
        final List<String> seenRooms = notebook.getSeenRooms();

        assertTrue(seenSuspects.contains(PROFESSOR_PLUM));
        assertTrue(seenWeapons.contains(REVOLVER));
        assertTrue(seenRooms.contains(KITCHEN));

        assertEquals(1, seenSuspects.size());
        assertEquals(1, seenWeapons.size());
        assertEquals(1, seenRooms.size());
    }

    /**
     * Tests the logSeenCard method by simulating the player seeing a card during the game.
     * Ensures that the seen card is marked as seen in the notebook.
     */
    @Test
    void testLogSeenCards() {
        notebook.logSeenCards(MISS_SCARLET);
        notebook.logSeenCards(CANDLESTICK);
        notebook.logSeenCards(BALLROOM);

        final List<String> seenSuspects = notebook.getSeenSuspects();
        final List<String> seenWeapons = notebook.getSeenWeapons();
        final List<String> seenRooms = notebook.getSeenRooms();

        assertTrue(seenSuspects.contains(MISS_SCARLET));
        assertTrue(seenWeapons.contains(CANDLESTICK));
        assertTrue(seenRooms.contains(BALLROOM));

        assertEquals(1, seenSuspects.size());
        assertEquals(1, seenWeapons.size());
        assertEquals(1, seenRooms.size());
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

        final List<String> seenSuspects = notebook.getSeenSuspects();
        final List<String> seenWeapons = notebook.getSeenWeapons();
        final List<String> seenRooms = notebook.getSeenRooms();

        assertTrue(seenSuspects.contains("Colonel Mustard"));
        assertTrue(seenSuspects.contains(MISS_SCARLET));
        assertTrue(seenWeapons.contains("Rope"));
        assertTrue(seenWeapons.contains(CANDLESTICK));
        assertTrue(seenRooms.contains("Conservatory"));

        assertEquals(2, seenSuspects.size());
        assertEquals(2, seenWeapons.size());
        assertEquals(1, seenRooms.size());
    }
}
