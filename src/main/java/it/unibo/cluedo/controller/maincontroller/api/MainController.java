package it.unibo.cluedo.controller.maincontroller.api;

import java.util.List;

import it.unibo.cluedo.controller.accusationcontroller.api.AccusationController;
import it.unibo.cluedo.controller.accusationcontroller.api.FinalAccusationController;
import it.unibo.cluedo.controller.dicecontroller.api.DiceController;
import it.unibo.cluedo.controller.gamesolutioncontroller.api.GameSolutionController;
import it.unibo.cluedo.controller.joystickcontroller.api.JoystickController;
import it.unibo.cluedo.controller.mapsetupcontroller.impl.MapSetupController;
import it.unibo.cluedo.controller.notebookcontroller.api.NotebookController;
import it.unibo.cluedo.controller.statisticscontroller.api.StatisticsController;
import it.unibo.cluedo.controller.unforeseencontroller.api.UnforeseenController;
import it.unibo.cluedo.controller.gamemenucontroller.api.GameMenuController;

import it.unibo.cluedo.model.GameModel;

/**
 * MainController is the primary interface that provides access to the model
 * and to the various controllers related to the game.
 */
public interface MainController {
    /**
     * Starts the game view by creating a GameMenuView.
     */
    void startView();

    /**
     * Display the main game frame view.
     */
    void displayMainFrame();

    /**
     * Initializes game model.
     * 
     * @param playerNames a list of player names to be set
     * @param playerColors a list of player colors to be set
     */
    void initializeGameModel(List<String> playerNames, List<String> playerColors);

    /**
     * Returns the current game model instance.
     *
     * @return the current game model instance
     */
    GameModel getGameInstance();

    /**
     * Returns a list of the current player's cards path.
     *
     * @return a list of the current player's cards path
     */
    List<String> getCurrentPlayerCardsPaths();

    /**
     * Returns a instance of NotebookController.
     * @return the NotebookController
     */
    NotebookController getNotebookController();

    /**
     * Returns a instance of GameSolutionController.
     * @return the GameSolutionController
     */
    GameSolutionController getGameSolutionController();

    /**
     * Returns a instance of MapSetUpController.
     * @return the MapSetUpController
     */
    MapSetupController getMapController();

    /**
     * Returns a instance of StatisticsController.
     * @return the StatisticsController
     */
    StatisticsController getStatisticsController();

    /**
     * Returns a instance of FinalAccusationController.
     * @return the FinalAccusationController
     */
    FinalAccusationController getFinalAccusationController();

    /**
    * Returns a instance of AccusationController.
    * @return the AccusationController
    */
    AccusationController getAccusationController();

    /**
     * Returns a instance of JoystickController.
     * @return a instance of JoystickController
     */
    JoystickController getJoystickController();

    /**
     * Returns a instance of UnforeseenController.
     * @return a instance of UnforeseenController
     */
    UnforeseenController getUnforeseenController();

    /**
     * Returns a instance of DiceController.
     * @return a instance of DiceController
     */
    DiceController getDiceController();

    /**
     * Returns a instance of GameMenuController.
     * @return a instance of GameMenuController
     */
    GameMenuController getGameMenuController();

    void updateBoard();

    void updateInformations();
}
