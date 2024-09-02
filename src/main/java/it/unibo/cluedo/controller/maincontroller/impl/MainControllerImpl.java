package it.unibo.cluedo.controller.maincontroller.impl;

import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.GameModelBuilderImpl;
import it.unibo.cluedo.model.deck.impl.DeckImpl;
import it.unibo.cluedo.view.maingamepanel.MainGamePanel;

/**
 * MainControllerImpl is responsible for initializing and managing the main game controller.
 * It sets up the game model with predefined players and starts the game view.
 */
public class MainControllerImpl {
    private final GameModel gameModel;

    /**
     * Constructs a new MainControllerImpl object.
     * The constructor initialize the game model using the GameModelBuilderImpl.
     */
    public MainControllerImpl() {
        this.gameModel = new GameModelBuilderImpl(new DeckImpl())
        .addPlayer("Alice", "Red")
        .addPlayer("Bob", "Green")
        .addPlayer("Charlie", "Blue")
        .withGameSolution()
        .build();
    }

    /**
     * Starts the game view by creating a new GamePanel.
     */
    public void startView() {
        new MainGamePanel();
    }

    /**
     * Returns the current game model instance.
     * 
     * @return the current game model instance
     */
    public GameModel getGameInstance() {
        return this.gameModel;
    }
}
