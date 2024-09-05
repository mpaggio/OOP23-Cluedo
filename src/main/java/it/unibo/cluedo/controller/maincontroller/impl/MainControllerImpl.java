package it.unibo.cluedo.controller.maincontroller.impl;

import java.util.List;
import java.util.ArrayList;

import it.unibo.cluedo.controller.accusationcontroller.api.AccusationController;
import it.unibo.cluedo.controller.accusationcontroller.api.FinalAccusationController;
import it.unibo.cluedo.controller.accusationcontroller.impl.AccusationControllerImpl;
import it.unibo.cluedo.controller.accusationcontroller.impl.FinalAccusationControllerImpl;
import it.unibo.cluedo.controller.dicecontroller.api.DiceController;
import it.unibo.cluedo.controller.gamesolutioncontroller.api.GameSolutionController;
import it.unibo.cluedo.controller.gamesolutioncontroller.impl.GameSolutionControllerImpl;
import it.unibo.cluedo.controller.joystickcontroller.api.JoystickController;
import it.unibo.cluedo.controller.joystickcontroller.impl.JoystickControllerImpl;
import it.unibo.cluedo.controller.maincontroller.api.MainController;
import it.unibo.cluedo.controller.mapsetupcontroller.impl.MapSetupController;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;
import it.unibo.cluedo.controller.notebookcontroller.impl.NotebookControllerImpl;
import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;
import it.unibo.cluedo.controller.statisticscontroller.impl.StatisticsControllerImpl;
import it.unibo.cluedo.controller.unforeseencontroller.api.UnforeseenController;
import it.unibo.cluedo.controller.unforeseencontroller.impl.UnforeseenControllerImpl;
import it.unibo.cluedo.controller.dicecontroller.impl.DiceControllerImpl;
import it.unibo.cluedo.controller.gamemenucontroller.api.GameMenuController;
import it.unibo.cluedo.controller.gamemenucontroller.impl.GameMenuControllerImpl;
import it.unibo.cluedo.controller.gamesavecontroller.api.GameSaveController;
import it.unibo.cluedo.controller.gamesavecontroller.impl.GameSaveControllerImpl;
import it.unibo.cluedo.model.GameModel;
import it.unibo.cluedo.model.GameModelBuilder;
import it.unibo.cluedo.model.GameModelBuilderImpl;
import it.unibo.cluedo.model.deck.impl.DeckImpl;
import it.unibo.cluedo.view.gamemenu.GameMenuView;
import it.unibo.cluedo.view.maingameframe.MainGameFrame;
import it.unibo.cluedo.model.card.api.Card;

/**
 * MainControllerImpl is responsible for initializing and managing the main game controller.
 * It sets up the game model with predefined players and starts the game view.
 */
public class MainControllerImpl implements MainController {
    private final GameSolutionController gameSolutionController;
    private final NotebookController notebookController;
    private final MapSetupController mapController;
    private final AccusationController accusationController;
    private final FinalAccusationController finalAccusationController;
    private final StatisticsController statisticsController;
    private final JoystickController joystickController;
    private final UnforeseenController unforeseenController;
    private final DiceController diceController;
    private final GameMenuController gameMenuController;
    private final GameSaveController gameSaveController;
    private MainGameFrame mainFrame;
    private GameModel gameModel;

    /**
     * Constructs a new MainControllerImpl object.
     * The constructor initialize the game model using the GameModelBuilderImpl.
     */
    public MainControllerImpl() {
        this.gameSolutionController = new GameSolutionControllerImpl();
        this.notebookController = new NotebookControllerImpl();
        this.mapController = new MapSetupController();
        this.accusationController = new AccusationControllerImpl();
        this.finalAccusationController = new FinalAccusationControllerImpl();
        this.statisticsController = new StatisticsControllerImpl();
        this.joystickController = new JoystickControllerImpl();
        this.unforeseenController = new UnforeseenControllerImpl();
        this.diceController = new DiceControllerImpl();
        this.gameMenuController = new GameMenuControllerImpl();
        this.gameSaveController = new GameSaveControllerImpl();
        this.mainFrame = null;
        this.gameModel = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startView() {
        new GameMenuView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayMainFrame() {
        this.mainFrame = new MainGameFrame();
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
     * {@inheritDoc}
     */
    @Override
    public AccusationController getAccusationController() {
        return this.accusationController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FinalAccusationController getFinalAccusationController() {
        return this.finalAccusationController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatisticsController getStatisticsController() {
        return this.statisticsController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JoystickController getJoystickController() {
        return this.joystickController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UnforeseenController getUnforeseenController() {
        return this.unforeseenController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiceController getDiceController() {
        return this.diceController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMenuController getGameMenuController() {
        return this.gameMenuController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeGameModel(final List<String> playerNames, final List<String> playerColors) {
        final GameModelBuilder builder = new GameModelBuilderImpl(new DeckImpl());
        for (int i = 0; i < playerNames.size(); i++) {
            builder.addPlayer(playerNames.get(i), playerColors.get(i));
        }
        this.gameModel = builder.withGameSolution().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBoard() {
        this.mainFrame.updateBoard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInformations() {
        this.mainFrame.updateInformations();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameSaveController getGameSaveController() {
        return this.gameSaveController;
    }
}
