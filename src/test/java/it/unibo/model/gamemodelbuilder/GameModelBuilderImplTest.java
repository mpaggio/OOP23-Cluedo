package it.unibo.model.gamemodelbuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.GameModelBuilder;
import it.unibo.cluedo.model.GameModelBuilderImpl;
/**
 * Test class for a {@link GameModelBuilderImpl} class.
 */
final class GameModelBuilderImplTest {

    private GameModelBuilder builder;
    private static final String PLAYER_1 = "Player1";
    private static final String PLAYER_2 = "Player2";
    private static final String PLAYER_3 = "Player3";
    private static final String COLOR_RED = "Red";
    private static final String COLOR_GREEN = "Green";
    private static final String COLOR_BLACK = "Black";

    /**
     * This is done before each test.
     */
    @BeforeEach
    void setUp() {
        builder = new GameModelBuilderImpl();
    }

    /*
     * Test that the builder added the correct number of players.
     */
    @Test
    void testCorrectNumberOfPlayers() {
        builder.addPlayer(PLAYER_1, COLOR_RED);
        builder.addPlayer(PLAYER_2, COLOR_GREEN);
        builder.addPlayer(PLAYER_3, COLOR_BLACK);
        builder.withGameSolution();
        final GameModel model = builder.build();
        assertEquals(3, model.getPlayers().size());
    }

    /**
     * Test that the builder throws an exception when the game solution is not set.
     */
    @Test
    void testBuildingWithoutSettingSolution() {
        builder.addPlayer(PLAYER_1, COLOR_RED);
        builder.addPlayer(PLAYER_2, COLOR_GREEN);
        builder.addPlayer(PLAYER_3, COLOR_BLACK);
        assertThrows(IllegalStateException.class, 
        () -> { 
            builder.build(); 
        }, "Building the model without setting the solution should throw an exception");
    }

    /**
     * Test the game model initialization.
     */
    @Test
    void testGameModelInitialization() {
        builder.addPlayer(PLAYER_1, COLOR_RED);
        builder.addPlayer(PLAYER_2, COLOR_GREEN);
        builder.addPlayer(PLAYER_3, COLOR_BLACK);
        builder.withGameSolution();
        final GameModel model = builder.build();
        assertEquals(3, model.getPlayers().size());
        assertEquals(3, model.getSolution().size());
    }
}
