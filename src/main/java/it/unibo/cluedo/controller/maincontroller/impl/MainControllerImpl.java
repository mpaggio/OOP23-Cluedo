package it.unibo.cluedo.controller.maincontroller.impl;

import java.util.List;
import java.util.ArrayList;

import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.GameModelBuilderImpl;
import it.unibo.cluedo.model.deck.impl.DeckImpl;
import it.unibo.cluedo.model.card.api.Card;
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

    /**
     * Returns a list of the current player's cards path.
     * 
     * @return a list of the current player's cards path
     */
    public List<String> getCurrentPlayerCardsPaths() {
        final List<String> cardsPaths = new ArrayList<>();
        for (final Card card : this.gameModel.getCurrentPlayer().getPlayerCards()) {
            cardsPaths.add(card.getImagePath());
        }
        return cardsPaths;
    }
}
