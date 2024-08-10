package it.unibo;

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

    /**
     * Set up the test environment by initializing the Notebook with a list of suspects,weapons and rooms.
     */
    @BeforeEach
    void setUp() {
        final List<String> allSuspects = Arrays.asList(PROFESSOR_PLUM, "Miss Scarlett", "Colonel Mustard");
        final List<String> allWeapons = Arrays.asList(CANDLESTICK, "Dagger", "Lead Pipe");
        final List<String> allRooms = Arrays.asList(KITCHEN, "Ballroom", "Conservatory");
        notebook = new NotebookImpl(allSuspects, allWeapons, allRooms);
    }

    /**
     * Tests the logSuspect method bu logging a suspect and checking that
     * the suspect is removed from the list of unselected suspects.
     */
    @Test
    void testLogSuspect() {
        notebook.logSuspect(PROFESSOR_PLUM);
        final List<String> unselectedSuspects = notebook.getUnselectedSuspects();
        assertFalse(unselectedSuspects.contains(PROFESSOR_PLUM));
        assertEquals(2, unselectedSuspects.size());
    }

    /**
     * Tests the logWeapon method by logging a weapon and checking that the weapon is removed from the list of unselected weapons.
     */
    @Test
    void testLogWeapon() {
        notebook.logWeapon(CANDLESTICK);
        final List<String> unselectedWeapons = notebook.getUnselectedWeapons();
        assertFalse(unselectedWeapons.contains(CANDLESTICK));
        assertEquals(2, unselectedWeapons.size());
    }

    /**
     * Tests the logRoom method by logging a room and checking that the room is removed from the list of unselected rooms.
     */
    @Test
    void testLogRoom() {
        notebook.logRoom(KITCHEN);
        final List<String> unselectedRooms = notebook.getUnselectedRooms();
        assertFalse(unselectedRooms.contains(KITCHEN));
        assertEquals(2, unselectedRooms.size());
    }

    /**
     * Tests the behavior when multiple suspects, weapons, and rooms are logged.
     * Ensures that the correct items are removed from their respective lists and that
     * the sizes of the lists are correct.
     */
    @Test
    void testMultipleLogs() {
        notebook.logSuspect(PROFESSOR_PLUM);
        notebook.logSuspect("Miss Scarlett");
        notebook.logWeapon(CANDLESTICK);
        notebook.logRoom(KITCHEN);


        final List<String> unselectedSuspects = notebook.getUnselectedSuspects();
        final List<String> unselectedWeapons = notebook.getUnselectedWeapons();
        final List<String> unselectedRooms = notebook.getUnselectedRooms();

        assertFalse(unselectedSuspects.contains(PROFESSOR_PLUM));
        assertFalse(unselectedSuspects.contains("Miss Scarlett"));
        assertFalse(unselectedWeapons.contains(CANDLESTICK));
        assertFalse(unselectedRooms.contains(KITCHEN));

        assertEquals(1, unselectedSuspects.size());
        assertEquals(2, unselectedWeapons.size());
        assertEquals(2, unselectedRooms.size());
    }
}
