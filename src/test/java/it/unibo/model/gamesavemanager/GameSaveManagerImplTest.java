package it.unibo.model.gamesavemanager;

import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.utilities.Position;
import it.unibo.cluedo.model.player.api.Player;
import it.unibo.cluedo.model.GameSaveManagerImpl;
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
import it.unibo.cluedo.model.card.api.Card;

class GameSaveManagerImplTest {
    private static final String FILE_NAME = "cluedo_saved_games.txt";
    private static final String TEST_FILE_NAME = "test_cluedo_saved_games.txt";
    private GameSaveManagerImpl gameSaveManager;
    private MutablePlayer player;

    @BeforeEach
    void setUp() {
        gameSaveManager = new GameSaveManagerImpl();
        player = new MutablePlayerImpl("TestPlayer", "Red");
        player.setPosition(new Position(0, 0));
        player.setPlayerCards(new ArrayList<Card>());
    }

    @AfterEach
    void tearDown() {
        final File file = new File(TEST_FILE_NAME);
        if (file.exists() && !file.delete()) {
            throw new IllegalStateException("Cannot delete the file " + TEST_FILE_NAME);
        }
    }

    @Test
    void testSaveGame() {
        final List<Player> players = new ArrayList<>();
        players.add(player);
        gameSaveManager.saveGame(players);
        final File file = new File(FILE_NAME);
        assertTrue(file.exists());
        final List<String> savedGames = gameSaveManager.viewSavedGames();
        assertFalse(savedGames.isEmpty());
    }

    @Test
    void testViewSavedGames() throws IOException {
        final List<Player> players = new ArrayList<>();
        players.add(player);
        gameSaveManager.saveGame(players);
        final List<String> savedGames = gameSaveManager.viewSavedGames();
        assertFalse(savedGames.isEmpty());
        assertTrue(savedGames.stream().anyMatch(s -> s.contains("TestPlayer")));
    }

    @Test
    void testGetOutputSavedGames() {
        final List<Player> players = new ArrayList<>();
        players.add(player);
        gameSaveManager.saveGame(players);
        final Optional<String> output = gameSaveManager.getOutputSavedGames();
        assertTrue(output.isPresent());
        assertTrue(output.get().contains("TestPlayer"));
    }

    @Test
    void testSaveGameWithEmptyPlayerList() {
        final List<Player> players = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> gameSaveManager.saveGame(players));
    }

    @Test
    void testSaveGameWithNullPlayerList() {
        assertThrows(IllegalArgumentException.class, () -> gameSaveManager.saveGame(null));
    }

}
