package it.unibo.cluedo.controller.maincontroller.impl;

import java.util.List;
import java.util.ArrayList;

import it.unibo.cluedo.controller.gamesolutioncontroller.api.GameSolutionController;
import it.unibo.cluedo.controller.gamesolutioncontroller.impl.GameSolutionControllerImpl;
import it.unibo.cluedo.controller.maincontroller.api.MainController;
import it.unibo.cluedo.controller.mapsetupcontroller.impl.MapSetupController;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;
import it.unibo.cluedo.controller.notebookcontroller.impl.NotebookControllerImpl;
import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.GameModelBuilderImpl;
import it.unibo.cluedo.model.deck.impl.DeckImpl;
import it.unibo.cluedo.view.maingameframe.MainGameFrame;
import it.unibo.cluedo.model.card.api.Card;

/**
 * MainControllerImpl is responsible for initializing and managing the main game controller.
 * It sets up the game model with predefined players and starts the game view.
 */
public class MainControllerImpl implements MainController {
    private final GameModel gameModel;
    private final GameSolutionController gameSolutionController;
    private final NotebookController notebookController;
    private final MapSetupController mapController;

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
        this.gameSolutionController = new GameSolutionControllerImpl();
        //this.gameSolutionController = new GameSolutionControllerImpl();
        this.notebookController = new NotebookControllerImpl();
        this.mapController = new MapSetupController();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startView() {
        new MainGameFrame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameModel getGameInstance() {
        return this.gameModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getCurrentPlayerCardsPaths() {
        final List<String> cardsPaths = new ArrayList<>();
        for (final Card card : this.gameModel.getCurrentPlayer().getPlayerCards()) {
            cardsPaths.add(card.getImagePath());
        }
        return cardsPaths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameSolutionController getGameSolutionController() {
        return this.gameSolutionController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotebookController getNotebookController() {
        return this.notebookController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapSetupController getMapController() {
        return this.mapController;
    }
}
