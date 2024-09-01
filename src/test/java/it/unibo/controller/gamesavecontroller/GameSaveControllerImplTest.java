package it.unibo.controller.gamesavecontroller;

import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.utilities.Position;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import it.unibo.cluedo.controller.gamesavecontroller.impl.GameSaveControllerImpl;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.card.api.Card;
import it.unibo.cluedo.model.component.api.MapComponent;

/**
 * This class is used to test the GameSaveControllerImpl class.
 */
class GameSaveControllerImplTest {

    private static final String FILE_NAME = "cluedo_saved_games.txt";
    private GameSaveControllerImpl gameSaveManager;
    private MutablePlayer player;
    private BoardImpl map;

    @BeforeEach
    void setUp() {
        gameSaveManager = new GameSaveControllerImpl();
        player = new MutablePlayerImpl("TestPlayer", "Red");
        player.setPosition(new Position(0, 0));
        player.setPlayerCards(new ArrayList<Card>());
        map = new BoardImpl();
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
        final List<Player> players = new ArrayList<>();
        final List<MapComponent> mapComponents = map.getMap();
        players.add(player);
        gameSaveManager.saveGame(players, mapComponents, map.getVisitor());
        final File file = new File(FILE_NAME);
        assertTrue(file.exists());
        final List<String> savedGames = gameSaveManager.viewSavedGames();
        assertFalse(savedGames.isEmpty());
    }

    /**
     * Test the viewSavedGames method.
     * @throws IOException
     */
    @Test
    void testViewSavedGames() throws IOException {
        final List<Player> players = new ArrayList<>();
        final List<MapComponent> mapComponents = map.getMap();
        players.add(player);
        gameSaveManager.saveGame(players, mapComponents, map.getVisitor());
        final List<String> savedGames = gameSaveManager.viewSavedGames();
        assertFalse(savedGames.isEmpty());
        assertTrue(savedGames.stream().anyMatch(s -> s.contains("TestPlayer")));
    }

    /**
     * Test if output of the saved games is not empty.
     */
    @Test
    void testGetOutputSavedGames() {
        final List<Player> players = new ArrayList<>();
        final List<MapComponent> mapComponents = map.getMap();
        players.add(player);
        gameSaveManager.saveGame(players, mapComponents, map.getVisitor());
        final Optional<String> output = gameSaveManager.getOutputSavedGames();
        assertTrue(output.isPresent());
        assertTrue(output.get().contains("TestPlayer"));
    }

    /**
     * Test the saveGame method with an empty player list.
     */
    @Test
    void testSaveGameWithEmptyPlayerList() {
        final List<Player> players = new ArrayList<>();
        final List<MapComponent> mapComponents = map.getMap();
        assertThrows(IllegalArgumentException.class, () -> gameSaveManager.saveGame(players, mapComponents, map.getVisitor()));
    }

    /**
     * Test the saveGame method with a null player list.
     */
    @Test
    void testSaveGameWithNullPlayerList() {
        final List<MapComponent> mapComponents = map.getMap();
        assertThrows(IllegalArgumentException.class, () -> gameSaveManager.saveGame(null, mapComponents, map.getVisitor()));
    }
}
