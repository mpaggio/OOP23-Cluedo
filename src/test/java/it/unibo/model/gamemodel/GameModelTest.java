package it.unibo.model.gamemodel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.GameModelBuilder;
import it.unibo.cluedo.model.GameModelBuilderImpl;
import it.unibo.cluedo.model.player.api.MutablePlayer;
import it.unibo.cluedo.utilities.TurnFase;

final class GameModelTest {
    
    private static final int MAX_DICE_RESULT = 6;

    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        final GameModelBuilder builder = new GameModelBuilderImpl();
        builder.addPlayer("player1", "red");
        builder.addPlayer("player2", "blue");
        builder.addPlayer("player3", "green");
        builder.withGameSolution();
        gameModel = builder.build();
    }

    @Test
    void testRollDice() {
        assertTrue(gameModel.getTurnFase().equals(TurnFase.ROLL_DICE));
        gameModel.rollDice();
        assertTrue(gameModel.getDiceResult() > 0
            && gameModel.getDiceResult() <= MAX_DICE_RESULT);
        assertTrue(gameModel.getTurnFase().equals(TurnFase.DRAW_UNFORESEEN));
        gameModel.endTurn();
        if (gameModel.getCurrentPlayer() instanceof MutablePlayer) {
            ((MutablePlayer) gameModel.getCurrentPlayer()).setDoubleRollDice(true);
        }
        gameModel.rollDice();
        assertTrue(gameModel.getDiceResult() > 0
            && gameModel.getDiceResult() <= MAX_DICE_RESULT);
        assertTrue(gameModel.getTurnFase().equals(TurnFase.MOVE_PLAYER));
    }

    @Test
    void testEndTurn() {
        assertTrue(gameModel.getTurnFase().equals(TurnFase.ROLL_DICE));
        assertTrue(gameModel.getCurrentPlayer().getUsername().equals("player1"));
        gameModel.endTurn();
        assertTrue(gameModel.getTurnFase().equals(TurnFase.ROLL_DICE));
        assertTrue(gameModel.getCurrentPlayer().getUsername().equals("player2"));
        gameModel.endTurn();
        gameModel.endTurn();
        assertTrue(gameModel.getTurnFase().equals(TurnFase.ROLL_DICE));
        assertTrue(gameModel.getCurrentPlayer().getUsername().equals("player1"));
    }
}
