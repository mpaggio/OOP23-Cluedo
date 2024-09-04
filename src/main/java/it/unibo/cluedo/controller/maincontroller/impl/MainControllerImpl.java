package it.unibo.cluedo.controller.maincontroller.impl;

import java.util.List;
import java.util.ArrayList;

import it.unibo.cluedo.controller.accusationcontroller.api.AccusationController;
import it.unibo.cluedo.controller.accusationcontroller.api.FinalAccusationController;
import it.unibo.cluedo.controller.accusationcontroller.impl.AccusationControllerImpl;
import it.unibo.cluedo.controller.accusationcontroller.impl.FinalAccusationControllerImpl;
import it.unibo.cluedo.controller.gamesolutioncontroller.api.GameSolutionController;
import it.unibo.cluedo.controller.gamesolutioncontroller.impl.GameSolutionControllerImpl;
import it.unibo.cluedo.controller.maincontroller.api.MainController;
import it.unibo.cluedo.controller.mapsetupcontroller.impl.MapSetupController;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;
import it.unibo.cluedo.controller.notebookcontroller.impl.NotebookControllerImpl;
import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;
import it.unibo.cluedo.controller.statisticscontroller.impl.StatisticsControllerImpl;
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
    private final AccusationController accusationController;
    private final FinalAccusationController finalAccusationController;
    private final StatisticsController statisticsController;

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
        this.accusationController = new AccusationControllerImpl();
        this.finalAccusationController = new FinalAccusationControllerImpl();
        this.statisticsController = new StatisticsControllerImpl();
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

    /**
     * Returns a instance of AccusationController.
     * @return the AccusationController
     */
    public AccusationController getAccusationController() {
        return this.accusationController;
    }

    /**
     * Returns a instance of FinalAccusationController.
     * @return the FinalAccusationController
     */
    public FinalAccusationController getFinalAccusationController() {
        return this.finalAccusationController;
    }

    /**
     * Returns a instance of StatisticsController.
     * @return the StatisticsController
     */
    public StatisticsController getStatisticsController() {
        return this.statisticsController;
    }
}
