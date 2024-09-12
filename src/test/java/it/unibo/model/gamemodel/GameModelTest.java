package it.unibo.model.gamemodel;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.GameModelBuilder;
import it.unibo.cluedo.model.GameModelBuilderImpl;

final class GameModelTest {
    
    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        final GameModelBuilder builder = new GameModelBuilderImpl();
        builder.addPlayer("player1", "red");
        builder.addPlayer("player2", "blue");
        builder.addPlayer("player3", "green");
        gameModel = builder.build();
    }

}
