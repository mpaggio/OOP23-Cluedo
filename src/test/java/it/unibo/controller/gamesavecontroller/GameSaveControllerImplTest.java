package it.unibo.controller.gamesavecontroller;

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
import it.unibo.cluedo.controller.gamesavecontroller.api.GameSaveController;
import it.unibo.cluedo.model.board.impl.BoardImpl;
import it.unibo.cluedo.model.player.impl.MutablePlayerImpl;
import it.unibo.cluedo.model.card.api.Card.Type;
import it.unibo.cluedo.model.card.impl.CardImpl;
import it.unibo.cluedo.model.board.api.Board;
import it.unibo.cluedo.model.GameModel;

/**
 * This class is used to test the GameSaveControllerImpl class.
 */
class GameSaveControllerImplTest {

    private static final String FILE_NAME = "cluedo_saved_games.txt";
    private GameSaveController gameSaveController;
    private GameModel gameModel;
    private List<Player> players;
    private Board map;


    @BeforeEach
    void setUp() {
        gameSaveController = new GameSaveControllerImpl();
        players = new ArrayList<>();

        MutablePlayerImpl player1 =  new MutablePlayerImpl("TestPlayer", "Red");
        player1.setPosition(new Position(1, 1));
        player1.setPlayerCards(List.of(
                new CardImpl(Type.ROOM , "TestWeapon",""),
                new CardImpl(Type.ROOM, "TestRoom", "")
        ));
        players.add(player1);

        MutablePlayer player2 =  new MutablePlayerImpl("TestPlayer", "Red");
        player2.setPosition(new Position(1, 1));
        player2.setPlayerCards(List.of(
                new CardImpl(Type.WEAPON, "TestWeapon", ""),
                new CardImpl(Type.ROOM, "TestRoom", "")
        ));
        players.add(player2);

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
        gameSaveController.saveGame();
        final File file = new File(FILE_NAME);
        assertTrue(file.exists());
        final List<String> savedGames = gameSaveController.viewSavedGames();
        assertFalse(savedGames.isEmpty());
    }

    /**
     * Test the viewSavedGames method.
     *
     * @throws IOException
     */
    @Test
    void testViewSavedGames() throws IOException {
        gameSaveController.saveGame();
        final List<String> savedGames = gameSaveController.viewSavedGames();
        assertFalse(savedGames.isEmpty());
        assertTrue(savedGames.stream().anyMatch(s -> s.contains("TestPlayer")));
    }

    /**
     * Test if output of the saved games is not empty.
     */
    @Test
    void testGetOutputSavedGames() {
        gameSaveController.saveGame();
        final Optional<String> output = gameSaveController.getOutputSavedGames();
        assertTrue(output.isPresent());
        assertTrue(output.get().contains("TestPlayer"));
    }

    /**
     * Test the saveGame method with an empty player list.
     */
    @Test
    void testSaveGameWithEmptyPlayerList() {
        assertThrows(IllegalArgumentException.class, () -> gameSaveController.saveGame());
    }

    /**
     * Test the saveGame method with a null player list.
     */
    @Test
    void testSaveGameWithNullPlayerList() {
        assertThrows(IllegalArgumentException.class, () -> gameSaveController.saveGame());
    }
}
